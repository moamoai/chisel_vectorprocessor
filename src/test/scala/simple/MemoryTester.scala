package simple

import chisel3._
import chisel3.iotesters.PeekPokeTester
import scala.util.control.Breaks._

/**
 * Test the Alu design
 */
class MemoryTester(dut: Memory) extends PeekPokeTester(dut) {
  def f_writeMem(addr: Int, wdata: Int) : Int = {
    poke(dut.io.addr , addr )
    poke(dut.io.wdata, wdata)
    poke(dut.io.we, 1.U)
    step(1)
    poke(dut.io.we, 0.U)
    return 0;
  }
  def f_readMem(addr: Int) : BigInt = {
    poke(dut.io.addr , addr )
    step(1)
    var rdata = peek(dut.io.rdata)
    return rdata;
  }

  // This is exhaustive testing, which usually is not possible
  for (i  <- 0 to 10 by 1) {
    for (j <- 1 to 1) {
      var addr  = rnd.nextInt((1<<16)) // i
      var wdata = 0
      f_writeMem(addr, wdata)
      var rdata = f_readMem (addr)
      println(f"Init Test [0x$i%03d] addr[0x$addr%08x]" 
            + f"wdata[0x$wdata%08x] rdata[0x$rdata%08x]");
      expect(dut.io.rdata, 0)
      // if(rdata != 0){
      //  println("[NG] init test")
      // }

      wdata = rnd.nextInt((1<<16)) // j
      f_writeMem(addr, wdata)
      rdata = f_readMem (addr)
      println(f"Write Test[0x$i%03d] addr[0x$addr%08x]" 
            + f"wdata[0x$wdata%08x] rdata[0x$rdata%08x]");
      expect(dut.io.rdata, wdata)
      // if(rdata != wdata){
      //  println("[NG] wdata test")
      // }
    }
  }
}

object MemoryTester extends App {
  println("Testing the ALU")
  iotesters.Driver.execute(Array[String]("--generate-vcd-output", "on"), () => new Memory()) {
    c => new MemoryTester(c)
  }
}
