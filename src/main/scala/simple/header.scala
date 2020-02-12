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

object OBJ_ALU_FUNC {
  val Add = 0.U(2.W)
  val Sub = 1.U(2.W)
  val Or  = 2.U(2.W)
  val And = 3.U(2.W)
}

object REG {
  val RD_0  = 0.U(5.W)
  val RD_1  = 1.U(5.W)
  val RD_2  = 2.U(5.W)
  val RD_3  = 3.U(5.W)
  val RD_4  = 4.U(5.W)
  val RD_5  = 5.U(5.W)
  val RD_6  = 6.U(5.W)
  val RD_7  = 7.U(5.W)
  val RD_8  = 8.U(5.W)

  val RS1_0  = 0.U(5.W)
  val RS1_1  = 1.U(5.W)
  val RS1_2  = 2.U(5.W)
  val RS1_3  = 3.U(5.W)
  val RS1_4  = 4.U(5.W)
  val RS1_5  = 5.U(5.W)
  val RS1_6  = 6.U(5.W)
  val RS1_7  = 7.U(5.W)
  val RS1_8  = 8.U(5.W)

  val RS2_0  = 0.U(5.W)
  val RS2_1  = 1.U(5.W)
  val RS2_2  = 2.U(5.W)
  val RS2_3  = 3.U(5.W)
  val RS2_4  = 4.U(5.W)
  val RS2_5  = 5.U(5.W)
  val RS2_6  = 6.U(5.W)
  val RS2_7  = 7.U(5.W)
  val RS2_8  = 8.U(5.W)
}