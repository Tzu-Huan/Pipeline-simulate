
public class IDEX {
	int RegDst;
	int ALUSrc;
	int ALUOp;
	int MemRead;
	int Memwrite;
	int Branch;
	int MemToReg;
	int RegWrite;
	int ReadReg1Value;
	int ReadReg2Value;
	int SEOffset;
	int WriteReg_20_16;
	int WriteReg_15_11;
	int Function; //0x20
	
	public void setRegDst(int RegDst){
		this.RegDst = RegDst;
	}
}