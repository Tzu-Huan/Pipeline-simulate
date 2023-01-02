
public class functions {
	
	public void IF_stage(int instructions, IFID ifidwrite){
		ifidwrite.Inst = instructions;
		//int[] IFIDwrite= {int instrcution, int IncrPC};
		//IFIDwrite[0] = instructions;
		//IFIDwrite[1] = IncrPC;

	}
	
	public void ID_stage(int[] Regs, IFID ifidread, IDEX idexwrite) {
		int FirstInstruction = ifidread.Inst;  //each read in instruction
		System.out.println(); //change line after each instruction
		//control signals
		int RegDst, ALUSrc, ALUOp, MemRead, Memwrite, Branch, MemToReg, RegWrite = 0;
		int ReadReg1Value, ReadReg2Value = 0;
		int SEOffset = 0;
		int WriteReg_20_16, WriteReg_15_11 = 0;
		int Function = 0;
		int opcodeMask = 0xFC000000;  // opcode mask
		int bitwiseAndResult = FirstInstruction & opcodeMask;  //bitwise AND
		

		int Shifted_bits_opcode;
		Shifted_bits_opcode = bitwiseAndResult >>> 26;

		//R format
		if (Shifted_bits_opcode == 0) {
			//System.out.println("R-format\n");
			
			//r format rs mask
			//System.out.println("R-rs");
			int rrsMask = 0b00000011111000000000000000000000;
			int bitwiseAndResultrrs = FirstInstruction & rrsMask;
			int Shifted_bits_rrs = bitwiseAndResultrrs >>> 21;
			//System.out.println(Shifted_bits_rrs);
			
			//r format rt mask
			//System.out.println("R-rt");
			int rrtMask = 0b00000000000111110000000000000000;
			int bitwiseAndResultrrt = FirstInstruction & rrtMask;
			int Shifted_bits_rrt = bitwiseAndResultrrt >>> 16;
			//System.out.println(Shifted_bits_rrt);
			
			//r format rd mask
			//System.out.println("R-rd");
			int rrdMask = 0b00000000000000001111100000000000;
			int bitwiseAndResultrrd = FirstInstruction & rrdMask;
			int Shifted_bits_rrd = bitwiseAndResultrrd >>> 11;
			//System.out.println(Shifted_bits_rrd);
			
			// r format shamt mask
			//System.out.println("R-shamt");
			int shamtMask = 0b11111100000000000000011111000000;
			int bitwiseAndResultshamt = FirstInstruction & shamtMask;
			int Shifted_bits_shamt = bitwiseAndResultshamt >>> 6;
			//System.out.println(Shifted_bits_shamt);
			
			// r format funct mask
			//System.out.println("R-funct");
			int functMask = 0b11111100000000000000000000111111;
			int bitwiseAndResultfunct = FirstInstruction & functMask;
			int Shifted_bits_funct = bitwiseAndResultfunct;
			//System.out.println(Shifted_bits_funct);
			
			switch (Shifted_bits_funct) {
			case 0:
				String fNoop = "NO OP";
				idexwrite.RegDst = 0;
				idexwrite.ALUSrc = 0;
				idexwrite.ALUOp = 0;
				idexwrite.MemRead = 0;
				idexwrite.Memwrite = 0;
				idexwrite.Branch = 0;
				idexwrite.MemToReg = 0;
				idexwrite.RegWrite = 0;
				idexwrite.ReadReg1Value = 0;
				idexwrite.ReadReg2Value = 0;
				idexwrite.SEOffset = 0;
				idexwrite.WriteReg_20_16 = 0;
				idexwrite.WriteReg_15_11 = 0;
				idexwrite.Function =0; //0x20
				break;
			case 32:
				String fAdd = "ADD";
				idexwrite.RegDst = 1;
				idexwrite.ALUSrc = 0;
				idexwrite.ALUOp = 0b10;
				idexwrite.MemRead = 0;
				idexwrite.Memwrite = 0;
				idexwrite.Branch = 0;
				idexwrite.MemToReg = 0;
				idexwrite.RegWrite = 1;
				idexwrite.ReadReg1Value = Regs[Shifted_bits_rrs];
				idexwrite.ReadReg2Value = Regs[Shifted_bits_rrt];
				idexwrite.SEOffset = 0;
				idexwrite.WriteReg_20_16 = Shifted_bits_rrt;
				idexwrite.WriteReg_15_11 = Shifted_bits_rrd;
				idexwrite.Function =32; //0x20
				//System.out.println(ifid.RegDst);
				//System.out.println(idexwrite.ReadReg1Value);
				//System.out.println(idexwrite.ReadReg2Value);
				//System.out.println(idexwrite.WriteReg_20_16);
				//System.out.println(idexwrite.WriteReg_15_11);
				//System.out.printf("%s $%d $%d $%d", fAdd, Shifted_bits_rrd,Shifted_bits_rrs,Shifted_bits_rrt);
				break;
			case 34:
				String fSub = "SUB";
				
				idexwrite.RegDst = 1;
				idexwrite.ALUSrc = 0;
				idexwrite.ALUOp = 0b10;
				idexwrite.MemRead = 0;
				idexwrite.Memwrite = 0;
				idexwrite.Branch = 0;
				idexwrite.MemToReg = 0;
				idexwrite.RegWrite = 1;
				idexwrite.ReadReg1Value = Regs[Shifted_bits_rrs];
				idexwrite.ReadReg2Value = Regs[Shifted_bits_rrt];
				idexwrite.SEOffset = 0;
				idexwrite.WriteReg_20_16 = Shifted_bits_rrt;
				idexwrite.WriteReg_15_11 = Shifted_bits_rrd;
				idexwrite.Function =34; //0x20
				
				
				//System.out.printf("%s $%d $%d $%d", fSub, Shifted_bits_rrd,Shifted_bits_rrs,Shifted_bits_rrt);
				break;

			}
			
			
			
		}else {
			//System.out.println("I-format");
			//i format rs mask
			int irsMask = 0b00000011111000000000000000000000;  //i
			int bitwiseAndResultirs = FirstInstruction & irsMask;
			int Shifted_bits_irs = bitwiseAndResultirs >>> 21;
			//System.out.println(Shifted_bits_irs);
			
			//i format rt mask
			int irtMask = 0b00000000000111110000000000000000;
			int bitwiseAndResultirt = FirstInstruction & irtMask;
			int Shifted_bits_irt = bitwiseAndResultirt >>> 16;
			//System.out.println(Shifted_bits_irt);
			
			//r format rd mask
			//System.out.println("R-rd");
			int irdMask = 0b00000000000000001111100000000000;
			int bitwiseAndResultird = FirstInstruction & irdMask;
			int Shifted_bits_ird = bitwiseAndResultird >>> 11;
			
			//offset
			short immediateMask = (short) 0b00000000000000001111111111111111;
			short bitwiseAndResultimmediate = (short) (FirstInstruction & immediateMask);
			short Shifted_bits_immediate = bitwiseAndResultimmediate;
			//System.out.println(Shifted_bits_immediate);
			
			switch(Shifted_bits_opcode) {

			case 32:
				String fLb = "LB";
				idexwrite.RegDst = 0;
				idexwrite.ALUSrc = 1;
				idexwrite.ALUOp = 0;
				idexwrite.MemRead = 1;
				idexwrite.Memwrite = 0;
				idexwrite.Branch = 0;
				idexwrite.MemToReg = 1;
				idexwrite.RegWrite = 1;
				
				idexwrite.ReadReg1Value = Regs[Shifted_bits_irs];
				idexwrite.ReadReg2Value = Regs[Shifted_bits_irt];
				idexwrite.SEOffset = Shifted_bits_immediate;
				idexwrite.WriteReg_20_16 = Shifted_bits_irt;
				idexwrite.WriteReg_15_11 = Shifted_bits_ird;
				idexwrite.Function =0;
				//idexwrite.Function =34; //0x20
				//System.out.printf("%s $%d, %d ($%d)", fLb, Shifted_bits_irt, Shifted_bits_immediate, Shifted_bits_irs);
				break;
			case 40:
				String fSb = "SB";
				idexwrite.RegDst = 0;  // X
				idexwrite.ALUSrc = 1;
				idexwrite.ALUOp = 0;
				idexwrite.MemRead = 0;
				idexwrite.Memwrite = 1;
				idexwrite.Branch = 0;
				idexwrite.MemToReg = 0;  // X
				idexwrite.RegWrite = 0;
				 
				idexwrite.ReadReg1Value = Regs[Shifted_bits_irs];
				idexwrite.ReadReg2Value = Regs[Shifted_bits_irt];
				//System.out.println("\ndfsaaaadddddd "+Regs[Shifted_bits_irt]+"\n");
				idexwrite.SEOffset = Shifted_bits_immediate;
				idexwrite.WriteReg_20_16 = Shifted_bits_irt;
				idexwrite.WriteReg_15_11 = Shifted_bits_ird;
				idexwrite.Function =0;
				//idexwrite.Function =34; //0x20
				//System.out.printf("%s $%d %d $%d", fSb, Shifted_bits_irt, Shifted_bits_immediate, Shifted_bits_irs);
				break;
			}
		
		}//IncrPC = IncrPC +4;  //next address need to be added 4
		
		//int RegDst, ALUSrc, ALUOp, MemRead, Memwrite, Branch, MemToReg, RegWrite = 0;

		
	}
	
	public void EX_stage(int[] Regs, IDEX idexread, EXMEM exmemwrite) {
		//ADD
		if(idexread.RegDst == 1 && idexread.ALUSrc == 0 && idexread.ALUOp == 0b10 && idexread.Function == 32) {
			//exmemwrite.CalcBTA = 0;
			exmemwrite.ALUResult = idexread.ReadReg1Value + idexread.ReadReg2Value;
			exmemwrite.WriteRegNum = idexread.WriteReg_15_11;
			exmemwrite.SWValue = idexread.ReadReg2Value;  //12/5 new
			exmemwrite.MemRead = 0;
			exmemwrite.Memwrite = 0;
			exmemwrite.Branch = 0;
			exmemwrite.MemToReg = 0;
			exmemwrite.RegWrite = 1;
		}
		//SUB
		else if(idexread.RegDst == 1 && idexread.ALUSrc == 0 && idexread.ALUOp == 0b10 && idexread.Function == 34) {
			exmemwrite.ALUResult = idexread.ReadReg1Value - idexread.ReadReg2Value;
			exmemwrite.WriteRegNum = idexread.WriteReg_15_11;
			exmemwrite.SWValue = idexread.ReadReg2Value;  //12/5 new
			exmemwrite.MemRead = 0;
			exmemwrite.Memwrite = 0;
			exmemwrite.Branch = 0;
			exmemwrite.MemToReg = 0;
			exmemwrite.RegWrite = 1;
		}
		//LB 
		else if(idexread.RegDst == 0 && idexread.ALUSrc == 1 && idexread.ALUOp == 0 && idexread.MemRead == 1) {
			exmemwrite.ALUResult = idexread.ReadReg1Value + idexread.SEOffset;
			exmemwrite.SWValue = idexread.ReadReg2Value;
			exmemwrite.WriteRegNum = idexread.WriteReg_20_16;
			
			exmemwrite.MemRead = 1;
			exmemwrite.Memwrite = 0;
			exmemwrite.Branch = 0;
			exmemwrite.MemToReg = 1;
			exmemwrite.RegWrite = 1;
			//System.out.println("herhe\n");
			//SB
		}else if (idexread.RegDst == 0 && idexread.ALUSrc == 1 && idexread.ALUOp == 0 && idexread.MemRead == 0){
			
			exmemwrite.ALUResult = idexread.ReadReg1Value + idexread.SEOffset;
			//System.out.println("\n\n\n\n\n\nadsfasdf"+idexread.SEOffset+"\n\n\n\n\n\n");
			exmemwrite.SWValue = idexread.ReadReg2Value;
			exmemwrite.WriteRegNum = idexread.WriteReg_20_16;
			
			exmemwrite.MemRead = 0;
			exmemwrite.Memwrite = 1;
			exmemwrite.Branch = 0;
			exmemwrite.MemToReg = 0;  // X
			exmemwrite.RegWrite = 0;
			//System.out.println("\n\ntest\n\n");
		}//NOP
		else if(idexread.RegDst == 0 && idexread.ALUSrc == 0 && idexread.ALUOp == 0 && idexread.Function == 0) {
			exmemwrite.ALUResult = 0;
			exmemwrite.SWValue = 0;
			exmemwrite.WriteRegNum = 0;
			exmemwrite.MemRead = 0;
			exmemwrite.Memwrite = 0;
			exmemwrite.Branch = 0;
			exmemwrite.MemToReg = 0;
			exmemwrite.RegWrite = 0;
		}
		
		else {
			System.out.println("");
		}
		
		
	}
	
	public void MEM_stage(int[] Regs, int[] Main_mem, EXMEM exmemread, MEMWB memwbwrite) {
		//System.out.println("\n\n\n\n\n\n" + exmemread.MemToReg +   "      " + memwbwrite.RegWrite + "\n\n\n\n");
		if(	exmemread.MemRead == 0 && exmemread.Memwrite == 0 && exmemread.Branch == 0 && exmemread.MemToReg == 0 && exmemread.RegWrite == 1) {
			memwbwrite.MemToReg = exmemread.MemToReg;
			memwbwrite.RegWrite = exmemread.RegWrite;
			
			memwbwrite.LWDataValue = 0;  // X
			memwbwrite.ALUResut = exmemread.ALUResult;
			memwbwrite.WriteRegNum = exmemread.WriteRegNum;
		}//LB
		else if (exmemread.MemRead == 1 && exmemread.Memwrite == 0 && exmemread.Branch == 0 && exmemread.MemToReg == 1 && exmemread.RegWrite == 1) {
			//System.out.println("\n"+Main_mem[exmemread.ALUResult]+"\n");
			//System.out.println("\n"+memwbwrite.LWDataValue+"\n");
			//System.out.println("\n"+exmemread.ALUResult+"\n");
			memwbwrite.MemToReg = exmemread.MemToReg;
			memwbwrite.RegWrite = exmemread.RegWrite;
			memwbwrite.LWDataValue = Main_mem[exmemread.ALUResult];
			memwbwrite.ALUResut = exmemread.ALUResult;
			memwbwrite.WriteRegNum = exmemread.WriteRegNum;
			//System.out.println("\n"+Main_mem[exmemread.ALUResult]+"\n");
			//System.out.println("\n"+memwbwrite.LWDataValue+"\n");
			//System.out.println("\n"+exmemread.ALUResult+"\n");
		}//SB
		else if(exmemread.MemRead == 0 && exmemread.Memwrite == 1 && exmemread.Branch == 0 && exmemread.RegWrite == 0) {  // exmemread.MemToReg == X
			//System.out.println("\ndfsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+Main_mem[exmemread.ALUResult]+"\n");
			memwbwrite.MemToReg = exmemread.MemToReg;
			memwbwrite.RegWrite = exmemread.RegWrite;
			Main_mem[exmemread.ALUResult] = exmemread.SWValue;
			//System.out.println("\ndfsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+exmemread.ALUResult+"\n");
			//System.out.println("\ndfsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+Main_mem[exmemread.ALUResult]+"\n");
		}//NOP
		else if(exmemread.MemRead == 0 && exmemread.Memwrite == 0 && exmemread.Branch == 0 && exmemread.RegWrite == 0) {
			memwbwrite.MemToReg = 0;
			memwbwrite.RegWrite = 0;
			
			memwbwrite.LWDataValue = 0;  // X
			memwbwrite.ALUResut = 0;
			memwbwrite.WriteRegNum = 0;
		}
		else {
			System.out.println("");
		}
	}
	
	public void WB_stage(int[] Regs, int[] Main_mem, MEMWB memwbread) {

		//System.out.println("\n\n\n\n\n\n" + memwbread.MemToReg +   "      " + memwbread.RegWrite + "\n\n\n\n");
		if(memwbread.MemToReg == 0 && memwbread.RegWrite == 1) {
			
			Regs[memwbread.WriteRegNum] = memwbread.ALUResut;
			//System.out.println("\n\n\n\n"+memwbread.WriteRegNum +"\n\n\n" +Regs[memwbread.WriteRegNum]+ "\n\n\n\n\n");
		}//LB
		else if (memwbread.MemToReg == 1 && memwbread.RegWrite == 1) {
			//System.out.println("\n\n\n\n\nn\n\test\n\n\n\n\n");
			Regs[memwbread.WriteRegNum] = memwbread.LWDataValue;
			//System.out.println("\nger "+memwbread.WriteRegNum+"\n");
			//System.out.println("\nger "+memwbread.LWDataValue+"\n");
		}//SB don't need to do anything in this stage
		//NOP
		else if(memwbread.MemToReg == 0 && memwbread.RegWrite == 0) {
			
		}
		else {
			
			System.out.println("");
		}
		//  /
		////
		 
		 
	}
	//String.format("%8s", 
      //      Integer.toBinaryString(idexread.SEOffset)).replaceAll(" ", "0")
	public void Print_out_everything(
		IFID ifidwrite,
		IFID ifidread,
		IDEX idexwrite,
		IDEX idexread,
		EXMEM exmemwrite,
		EXMEM exmemread,
		MEMWB memwbwrite, 
		MEMWB memwbread) {
		System.out.println("-----------------------------------------------------");
		//String binaryString = Integer.toString(n, 2);
		System.out.println("\nIF/ID_Write (written to by the IF stage)");
		System.out.printf("Inst = %08x\n",ifidwrite.Inst);
		System.out.println("\nIF/ID_Read (read by the ID stage)");
		System.out.printf("Inst = %08x\n",ifidread.Inst);
		System.out.println("\n-----------------------------------------------------");
		System.out.println("ID/EX_Write (written to by the ID stage)");
		//System.out.println("i: " + String.format("%2s", 
	    //        Integer.toBinaryString(idexwrite.ALUOp)).replaceAll(" ", "0"));
		System.out.printf("Control: RegDst = %d, ALUSrc = %d, ALUOp = %s, MemRead = %d, MemWrite = %d, MemToReg = %d, RegWrite = %d",
				idexwrite.RegDst, idexwrite.ALUSrc, String.format("%2s", 
			            Integer.toBinaryString(idexwrite.ALUOp)).replaceAll(" ", "0"), idexwrite.MemRead, idexwrite.Memwrite, idexwrite.MemToReg, idexwrite.RegWrite);
		System.out.printf( "\nReadReg1Value = %x ReadReg2Value = %x", idexwrite.ReadReg1Value, idexwrite.ReadReg2Value );
		System.out.printf("\nSEOffset = %08x WriteReg_20_16 = %d WriteReg_15_11 = %d Function = %x", 
				idexwrite.SEOffset, idexwrite.WriteReg_20_16, idexwrite.WriteReg_15_11, idexwrite.Function);
		//SEOffset = X WriteReg_20_16 = 6 WriteReg_15_11 = 7 Function = 20
		
		System.out.println("\n\nID/EX_Read (read by the EX stage)");
		System.out.printf("Control: RegDst = %d, ALUSrc = %d, ALUOp = %s, MemRead = %d, MemWrite = %d, MemToReg = %d, RegWrite = %d",
				idexread.RegDst, idexread.ALUSrc, String.format("%2s", 
			            Integer.toHexString(idexread.ALUOp)).replaceAll(" ", "0"), idexread.MemRead, idexread.Memwrite, idexread.MemToReg, idexread.RegWrite);
		System.out.printf( "\nReadReg1Value = %x ReadReg2Value = %x", idexread.ReadReg1Value, idexread.ReadReg2Value );
		System.out.printf("\nSEOffset = %08x WriteReg_20_16 = %d WriteReg_15_11 = %d Function = %x",idexread.SEOffset, idexread.WriteReg_20_16, idexread.WriteReg_15_11, idexread.Function);
		
		System.out.println("\n-----------------------------------------------------");
		System.out.println("EX/MEM_Write (written to by the EX stage)");
		System.out.printf("MemRead = %d, MemWrite = %d, MemToReg = %d, RegWrite = %d,", 
				exmemwrite.MemRead, exmemwrite.Memwrite, exmemwrite.MemToReg, exmemwrite.RegWrite);
		System.out.printf("\nALUResult = %x", exmemwrite.ALUResult);
		System.out.printf("\nSWValue = %x WriteRegNum = %d", exmemwrite.SWValue, exmemwrite.WriteRegNum);
		
		System.out.println("\n\nEX/MEM_Read (read by the MEM stage)");
		System.out.printf("MemRead = %d, MemWrite = %d, MemToReg = %d, RegWrite = %d,", 
				exmemread.MemRead, exmemread.Memwrite, exmemread.MemToReg, exmemread.RegWrite);
		System.out.printf("\nALUResult = %x", exmemread.ALUResult);
		System.out.printf(" SWValue = %x WriteRegNum = %d", exmemread.SWValue, exmemread.WriteRegNum);
		
		System.out.println("\n-----------------------------------------------------");
		System.out.println("MEM/WB_Write (written to by the MEM stage)");
		System.out.printf("Control: MemToReg = %d, RegWrite = %d", memwbwrite.MemToReg, memwbwrite.RegWrite);
		System.out.printf(" LWDataValue = %d ALUResult = %x WriteRegNum = %d", memwbwrite.LWDataValue, memwbwrite.ALUResut, memwbwrite.WriteRegNum);
		
		System.out.println("\n\nMEM/WB_Read (read by the WB stage)");
		System.out.printf("Control: MemToReg = %d, RegWrite = %d", memwbread.MemToReg, memwbread.RegWrite);
		System.out.printf("\nLWDataValue = %d ALUResult = %x WriteRegNum = %d", memwbread.LWDataValue, memwbread.ALUResut, memwbread.WriteRegNum);
		System.out.println("\n-----------------------------------------------------");
	}
	
	public void Copy_write_to_read(
		IFID ifidwrite,
		IFID ifidread,
		IDEX idexwrite,
		IDEX idexread,
		EXMEM exmemwrite,
		EXMEM exmemread,
		MEMWB memwbwrite, 
		MEMWB memwbread
		) {
		//IFID copy
		ifidread.Inst = ifidwrite.Inst;
		//IDEX copy
		idexread.RegDst = idexwrite.RegDst;
		idexread.ALUSrc = idexwrite.ALUSrc;
		idexread.ALUOp = idexwrite.ALUOp;
		idexread.MemRead = idexwrite.MemRead;
		idexread.Memwrite = idexwrite.Memwrite;
		idexread.Branch = idexwrite.Branch;
		idexread.MemToReg = idexwrite.MemToReg;
		idexread.RegWrite = idexwrite.RegWrite;
		idexread.ReadReg1Value = idexwrite.ReadReg1Value;
		idexread.ReadReg2Value = idexwrite.ReadReg2Value;
		idexread.SEOffset = idexwrite.SEOffset;
		idexread.WriteReg_20_16 = idexwrite.WriteReg_20_16;
		idexread.WriteReg_15_11 = idexwrite.WriteReg_15_11;
		idexread.Function = idexwrite.Function; //0x20

		//EXMEM copy
		exmemread.MemRead = exmemwrite.MemRead;
		exmemread.Memwrite = exmemwrite.Memwrite;
		exmemread.Branch = exmemwrite.Branch;
		exmemread.MemToReg = exmemwrite.MemToReg;
		exmemread.RegWrite = exmemwrite.RegWrite;
		
		exmemread.CalcBTA = exmemwrite.CalcBTA;  //calculate Branch Target Address
		exmemread.Zero = exmemwrite.Zero;  //zero flag (true or false) for  ADD example in class = False
		
		exmemread.ALUResult = exmemwrite.ALUResult;  //no use in r format
		
		exmemread.SWValue = exmemwrite.SWValue;
		exmemread.WriteRegNum = exmemwrite.WriteRegNum;
		
		//MEMWB copy
		memwbread.LWDataValue = memwbwrite.LWDataValue;
		memwbread.ALUResut = memwbwrite.ALUResut;
		memwbread.WriteRegNum = memwbwrite.WriteRegNum;
		memwbread.MemToReg = memwbwrite.MemToReg;
		memwbread.RegWrite = memwbwrite.RegWrite;

		
		
		
	}
	
}














