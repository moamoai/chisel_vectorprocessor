/*
 *
 * An ALU is a minimal start for a processor.
 *
 */

package simple

import chisel3._
import chisel3.util._

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
    val vector_length = Input (UInt(5.W))
    val ready         = Output(UInt(1.W))
  })

  // Use shorter variable names
  val valid         = io.valid;
  val op            = io.op   ;
  val rd            = io.rd   ;
  val rs1           = io.rs1  ;
  val rs2           = io.rs2  ;
  val mem_addr      = io.mem_addr;
  val vector_length = io.vector_length;

  val r_ready = RegInit(0.U(1.W))

  // val OP_Nop :: OP_Load :: OP_Store :: OP_Add OP_Sub :: Nil = Enum(6)
  val r_op = RegInit(0.U(4.W))

  // Controller
  when(valid === 1.U){
    r_op    := op
    r_ready := 0.U
  // }.elsewhen(op!=0.U){
  }.otherwise {
    r_ready := 1.U
  }


  // Output
  io.ready := r_ready
}

// Generate the Verilog code by invoking the Driver
object VectorProcessorMain extends App {
  println("Generating the VectorProcessor hardware")
  chisel3.Driver.execute(Array("--target-dir", "generated"), () => new VectorProcessor())
}

