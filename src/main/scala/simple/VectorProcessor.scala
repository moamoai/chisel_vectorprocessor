package simple

import chisel3._
import chisel3.util._

import chisel3.util.experimental.loadMemoryFromFile

/**
 * This is a very basic ALU example.
 */
class VectorProcessor extends Module {
  val io = IO(new Bundle {
    val valid         = Input (UInt(1.W))
    val op            = Input (UInt(4.W))
    val rd            = Input (UInt(4.W))
    val rs1           = Input (UInt(4.W))
    val rs2           = Input (UInt(4.W))
    val mem_addr      = Input (UInt(16.W))
    val imm           = Input (UInt(16.W))
    val vector_length = Input (UInt(5.W))
    val ready         = Output(UInt(1.W))
  })

  // Use shorter variable names
  val valid         = io.valid;
  val op            = io.op   ;
  val rd            = io.rd   ;
  val rs1           = io.rs1  ;
  val rs2           = io.rs2  ;
  val in_mem_addr   = io.mem_addr;
  val imm           = io.imm;
  val vector_length = io.vector_length;

  val r_ready_nop = RegInit(0.U(1.W))
  val r_ready_add = RegInit(0.U(1.W))
  val r_ready_ld  = RegInit(0.U(1.W))
  val r_ready_ldi = RegInit(0.U(1.W))
  val r_ready_sw  = RegInit(0.U(1.W))

  val r_op_ldi    = RegInit(0.U(1.W))
  val r_count     = RegInit(0.U(5.W))
  

  val r_RegFile = RegInit(VecInit(Seq.fill(32)(0.U(32.W))))

  // val OP_Nop :: OP_Load :: OP_Store :: OP_Add OP_Sub :: Nil = Enum(6)
  val r_op = RegInit(0.U(4.W))

  val mem_wdata = Wire(UInt(16.W))
  val mem_rdata = Wire(UInt(16.W))
  val mem_addr  = Wire(UInt(16.W))
  val mem_we    = Wire(UInt(1.W))
  mem_wdata := 0.U
  mem_we    := 0.U
  mem_addr  := 0.U

  // Memory
  val i_mem = Module(new Memory)
  i_mem.io.we    := mem_we
  i_mem.io.addr  := mem_addr
  i_mem.io.wdata := mem_wdata
  mem_rdata := i_mem.io.rdata

  // Controller
  when(valid === 1.U){
    r_op        := op
    r_ready_nop := 0.U
    r_ready_add := 0.U
    r_ready_ld  := 0.U
    r_ready_ldi := 0.U
    r_ready_sw  := 0.U
    r_count     := 0.U
  }.elsewhen(r_op === OBJ_OPCODE.OP_Nop){
    r_ready_nop := 1.U
  }.elsewhen(r_op === OBJ_OPCODE.OP_Loadimm){
    // for(i <- 0 to 32){
    when(r_count < vector_length){
      r_RegFile(rd + r_count) := imm
      r_count := r_count + 1.U
    }.elsewhen(r_count === vector_length){
      r_ready_ldi := 1.U
    }
  }.elsewhen(r_op === OBJ_OPCODE.OP_Store){
    when(r_count < vector_length){
      mem_we     := 1.U
      mem_addr   := in_mem_addr + r_count
      mem_wdata  := r_RegFile(rs1 + r_count)
      r_count    := r_count + 1.U
    }.elsewhen(r_count === vector_length){
      r_ready_sw := 1.U
    }
  }.otherwise {
  }


  // Output
  io.ready := r_ready_nop | r_ready_add | r_ready_ld | r_ready_ldi | r_ready_sw
  // io.ready := r_ready
}

// Generate the Verilog code by invoking the Driver
object VectorProcessorMain extends App {
  println("Generating the VectorProcessor hardware")
  chisel3.Driver.execute(Array("--target-dir", "generated"), () => new VectorProcessor())
}

