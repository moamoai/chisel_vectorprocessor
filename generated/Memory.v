module Memory(
  input         clock,
  input         reset,
  input         io_we,
  input  [15:0] io_wdata,
  input  [15:0] io_addr,
  output [15:0] io_rdata
);
  reg [15:0] my_mem [0:65535]; // @[Memory.scala 32:19]
  reg [31:0] _RAND_0;
  wire [15:0] my_mem__T_2_data; // @[Memory.scala 32:19]
  wire [15:0] my_mem__T_2_addr; // @[Memory.scala 32:19]
  wire [15:0] my_mem__T_1_data; // @[Memory.scala 32:19]
  wire [15:0] my_mem__T_1_addr; // @[Memory.scala 32:19]
  wire  my_mem__T_1_mask; // @[Memory.scala 32:19]
  wire  my_mem__T_1_en; // @[Memory.scala 32:19]
  assign my_mem__T_2_addr = io_addr;
  assign my_mem__T_2_data = my_mem[my_mem__T_2_addr]; // @[Memory.scala 32:19]
  assign my_mem__T_1_data = io_wdata;
  assign my_mem__T_1_addr = io_addr;
  assign my_mem__T_1_mask = 1'h1;
  assign my_mem__T_1_en = io_we;
  assign io_rdata = my_mem__T_2_data; // @[Memory.scala 41:12]
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
  _RAND_0 = {1{`RANDOM}};
  `ifdef RANDOMIZE_MEM_INIT
  for (initvar = 0; initvar < 65536; initvar = initvar+1)
    my_mem[initvar] = _RAND_0[15:0];
  `endif // RANDOMIZE_MEM_INIT
  `endif // RANDOMIZE
end // initial
`endif // SYNTHESIS
  always @(posedge clock) begin
    if(my_mem__T_1_en & my_mem__T_1_mask) begin
      my_mem[my_mem__T_1_addr] <= my_mem__T_1_data; // @[Memory.scala 32:19]
    end
  end
endmodule
