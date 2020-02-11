package simple

import chisel3._
import chisel3.util._

object OBJ_OPCODE {
  val OP_Nop  = 0.U(4.W)
  val OP_Load  = 1.U(4.W)
  val OP_Store  = 2.U(4.W)
  val OP_Add  = 3.U(4.W)
  val OP_Sub  = 4.U(4.W)
  val OP_Loadimm  = 5.U(4.W)
}