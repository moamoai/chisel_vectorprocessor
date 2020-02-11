#
# Building Chisel examples without too much sbt/scala/... stuff
#
# sbt looks for default into a folder ./project and . for build.sdt and Build.scala
# sbt creates per default a ./target folder

SBT = sbt

# Generate Verilog code

alu:
	$(SBT) "runMain simple.AluMain"

# Generate the C++ simulation and run the tests

alu-test:
	$(SBT) "test:runMain simple.AluTester --backend-name verilator"


GTKWAVE = /Applications/gtkwave.app/Contents/Resources/bin/gtkwave
view:
	$(GTKWAVE) ./test_run_dir/simple.AluTester1739761995/Alu.gtkw

# clean everything (including IntelliJ project settings)

clean:
	git clean -fd
