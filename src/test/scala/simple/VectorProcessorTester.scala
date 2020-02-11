package simple

import chisel3._
import chisel3.iotesters.PeekPokeTester
import scala.util.control.Breaks._

/**
 * Test the VectorProcessor design
 */
class VectorProcessorTester(dut: VectorProcessor) extends PeekPokeTester(dut) {
  def f_run_instruction(op: Int, rd: Int, rs1: Int, rs2: Int, mem_addr: Int, vector_length: Int) : Int = {
    poke(dut.io.valid        , 1.U          )
    poke(dut.io.op           , op           )
    poke(dut.io.rd           , rd           )
    poke(dut.io.rs1          , rs1          )
    poke(dut.io.rs2          , rs2          )
    poke(dut.io.mem_addr     , mem_addr     )
    poke(dut.io.vector_length, vector_length)
    step(1)
    poke(dut.io.valid        , 0.U          )
    return 0;
  }

  f_run_instruction(1, 1, 2, 3, 0x100, 4)

//  for (i  <- 0 to 1 by 1) {
//    for (j <- 1 to 1) {
//      var addr  = rnd.nextInt((1<<16)) // i
//      var wdata = 0
//      f_writeMem(addr, wdata)
//      var rdata = f_readMem (addr)
//      println(f"Init Test [0x$i%03d] addr[0x$addr%08x]" 
//            + f"wdata[0x$wdata%08x] rdata[0x$rdata%08x]");
//      expect(dut.io.rdata, 0)
//      // if(rdata != 0){
//      //  println("[NG] init test")
//      // }
//
//      wdata = rnd.nextInt((1<<16)) // j
//      f_writeMem(addr, wdata)
//      rdata = f_readMem (addr)
//      println(f"Write Test[0x$i%03d] addr[0x$addr%08x]" 
//            + f"wdata[0x$wdata%08x] rdata[0x$rdata%08x]");
//      expect(dut.io.rdata, wdata)
//      // if(rdata != wdata){
//      //  println("[NG] wdata test")
//      // }
//    }
//  }
}

object VectorProcessorTester extends App {
  println("Testing the ALU")
  iotesters.Driver.execute(Array[String]("--generate-vcd-output", "on"), () => new VectorProcessor()) {
    c => new VectorProcessorTester(c)
  }
}