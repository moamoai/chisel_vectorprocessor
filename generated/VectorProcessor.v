module VectorProcessor(
  input         clock,
  input         reset,
  input         io_valid,
  input  [3:0]  io_op,
  input  [3:0]  io_rd,
  input  [3:0]  io_rs1,
  input  [3:0]  io_rs2,
  input  [15:0] io_mem_addr,
  input  [15:0] io_imm,
  input  [4:0]  io_vector_length,
  output        io_ready
);
  reg  r_ready_nop; // @[VectorProcessor.scala 32:28]
  reg [31:0] _RAND_0;
  reg  r_ready_ldi; // @[VectorProcessor.scala 35:28]
  reg [31:0] _RAND_1;
  reg [4:0] r_count; // @[VectorProcessor.scala 38:28]
  reg [31:0] _RAND_2;
  reg [3:0] r_op; // @[VectorProcessor.scala 43:21]
  reg [31:0] _RAND_3;
  wire  _T_2; // @[VectorProcessor.scala 53:19]
  wire  _T_3; // @[VectorProcessor.scala 55:19]
  wire  _T_4; // @[VectorProcessor.scala 57:18]
  wire [4:0] _T_8; // @[VectorProcessor.scala 59:26]
  wire  _T_9; // @[VectorProcessor.scala 60:24]
  wire  _GEN_32; // @[VectorProcessor.scala 60:42]
  wire  _GEN_101; // @[VectorProcessor.scala 53:41]
  assign _T_2 = r_op == 4'h0; // @[VectorProcessor.scala 53:19]
  assign _T_3 = r_op == 4'h5; // @[VectorProcessor.scala 55:19]
  assign _T_4 = r_count < io_vector_length; // @[VectorProcessor.scala 57:18]
  assign _T_8 = r_count + 5'h1; // @[VectorProcessor.scala 59:26]
  assign _T_9 = r_count == io_vector_length; // @[VectorProcessor.scala 60:24]
  assign _GEN_32 = _T_9 | r_ready_ldi; // @[VectorProcessor.scala 60:42]
  assign _GEN_101 = _T_2 | r_ready_nop; // @[VectorProcessor.scala 53:41]
  assign io_ready = r_ready_nop | r_ready_ldi; // @[VectorProcessor.scala 68:12]
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
  `ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  r_ready_nop = _RAND_0[0:0];
  `endif // RANDOMIZE_REG_INIT
  `ifdef RANDOMIZE_REG_INIT
  _RAND_1 = {1{`RANDOM}};
  r_ready_ldi = _RAND_1[0:0];
  `endif // RANDOMIZE_REG_INIT
  `ifdef RANDOMIZE_REG_INIT
  _RAND_2 = {1{`RANDOM}};
  r_count = _RAND_2[4:0];
  `endif // RANDOMIZE_REG_INIT
  `ifdef RANDOMIZE_REG_INIT
  _RAND_3 = {1{`RANDOM}};
  r_op = _RAND_3[3:0];
  `endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`endif // SYNTHESIS
  always @(posedge clock) begin
    if (reset) begin
      r_ready_nop <= 1'h0;
    end else if (io_valid) begin
      r_ready_nop <= 1'h0;
    end else begin
      r_ready_nop <= _GEN_101;
    end
    if (reset) begin
      r_ready_ldi <= 1'h0;
    end else if (io_valid) begin
      r_ready_ldi <= 1'h0;
    end else if (!(_T_2)) begin
      if (_T_3) begin
        if (!(_T_4)) begin
          r_ready_ldi <= _GEN_32;
        end
      end
    end
    if (reset) begin
      r_count <= 5'h0;
    end else if (io_valid) begin
      r_count <= 5'h0;
    end else if (!(_T_2)) begin
      if (_T_3) begin
        if (_T_4) begin
          r_count <= _T_8;
        end
      end
    end
    if (reset) begin
      r_op <= 4'h0;
    end else if (io_valid) begin
      r_op <= io_op;
    end
  end
endmodule
