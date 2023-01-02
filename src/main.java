import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		
		
		// main memory
		int[] tmp = new int[256];
		for(int i=0; i<=0xFF; i++) {
			tmp[i] = i;
		}
		System.out.println(Arrays.toString(tmp));
		int[] Main_mem = new int[1024];
		for (int i= 0; i<1024; i++) {
			
			Main_mem[i]=tmp[i%tmp.length];
		}
		System.out.println(Arrays.toString(Main_mem));
		
		//registers
		int[] Regs = new int[32];
		Regs[0] = 0;
		for(int i=1; i<=31; i++) {
			Regs[i] = i + 0x100;
		}
		System.out.println(Arrays.toString(Regs));
		//pipeline registers (buffer)
		//int[] IFIDwrite= {100,200};
		//int[] IFIDread = {4, 5};
		//int[] IDEXwrite= {100,200};
		//int[] IDEXread = {4, 5};
		//int[] EXMEMwrite= {100,200};
		//int[] EXMEMread = {4, 5};
		//int[] MEMWBwrite= {100,200};
		//int[] MEMWBread = {4, 5};
		
		
		int [] instruction = new int[] {
				0xa1020000,  //SB $2 0x0($8)
				0x810AFFFC,  //LB $10 0xFFFC($8)  
				0x00831820,  //ADD $3 $4 $3
				0x01263820,  //ADD $7 $9 $6
				0x01224820,  //ADD $9 $9 $2
				0x81180000,  //LB $24 0x0($8)
				0x81510010,  //LB $17 0x10($10)
				0x00624022,  //SUB $8 $3 $2
				0x00000000,
				0x00000000,
				0x00000000,
				0x00000000
		};
		/*
		int [] instructions = new int[] {
			0x00a63820, 
			0x8d0f0004,
			0xad09fffc,
			0x00625022,
			0x10c8fffb,
			0x00000000,
			0x00000000,
			0x00000000,
			0x00000000
		};
		*/
		functions f = new functions();
		//int instruction = 0x00831820;
		
		IFID ifidwrite = new IFID();
		IFID ifidread = new IFID();
		
		IDEX idexwrite = new IDEX();
		IDEX idexread = new IDEX();
		
		EXMEM exmemwrite = new EXMEM();
		EXMEM exmemread = new EXMEM();
		
		MEMWB memwbwrite = new MEMWB();
		MEMWB memwbread = new MEMWB();
		for (int i=0; i<instruction.length; i++) {
			System.out.println("============================================================================================================================");
			System.out.println("\n\nThis is the "+(i+1)+" cycle.");
			f.IF_stage(instruction[i], ifidwrite);
			

			//IFID ifidread = new IFID();
			//ifidread.Inst = 0x01263820;
			
			
			//IFID ifidread = new IFID();
			f.ID_stage(Regs, ifidread, idexwrite);


			f.EX_stage(Regs, idexread, exmemwrite);

			f.MEM_stage(Regs, Main_mem, exmemread, memwbwrite);

			f.WB_stage(Regs, Main_mem, memwbread);

			f.Print_out_everything(
					 ifidwrite,
					 ifidread,
					 idexwrite,
					 idexread,
					 exmemwrite,
					 exmemread,
					 memwbwrite, 
					 memwbread);
			f.Copy_write_to_read(
					ifidwrite,
					ifidread,
					idexwrite,
					idexread,
					exmemwrite,
					exmemread,
					memwbwrite,
					memwbread
					);
			System.out.println("32 Registers: ");
			for(int k=0; k<Regs.length; k++) {
				System.out.printf("$%d (%d)", k, Regs[k]);
			}
			//Arrays.toString(Regs)
			
			
		}
		
		//IDEX idex = new IDEX();
		//System.out.println("fdf");
		//System.out.println(ifidread.RegDst);
		//f.IF_stage(instructions[0]);
		
		/*
		System.out.println(Arrays.toString(IFIDwrite));
		System.out.println(Arrays.toString(IFIDread));
		System.out.println(Arrays.toString(IDEXwrite));
		System.out.println(Arrays.toString(IDEXread));
		System.out.println(Arrays.toString(EXMEMwrite));
		System.out.println(Arrays.toString(EXMEMread));
		System.out.println(Arrays.toString(MEMWBwrite));
		System.out.println(Arrays.toString(MEMWBread));
		f.Copy_write_to_read(IFIDwrite, IFIDread, IDEXwrite, IDEXread, EXMEMwrite, EXMEMread, MEMWBwrite, MEMWBread);
		System.out.println("after");
		System.out.println(Arrays.toString(IFIDwrite));
		System.out.println(Arrays.toString(IFIDread));
		System.out.println(Arrays.toString(IDEXwrite));
		System.out.println(Arrays.toString(IDEXread));
		System.out.println(Arrays.toString(EXMEMwrite));
		System.out.println(Arrays.toString(EXMEMread));
		System.out.println(Arrays.toString(MEMWBwrite));
		System.out.println(Arrays.toString(MEMWBread));
		*/
	}

}



