
public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Funkt f1 = new Funkt();
		
		f1.sleep(1000 * 60 * 30);
		
		f1.pressButton();
		
		/*f1.initializeMouse();
		
		for(int i = 0; i < 515; i += 8){
			
			
			for(int j = 1; j < 8; j++){
				
				for(int k = 0; k < 515; k++){
					f1.sleep((long) 1.0);
					f1.mouseMove(i, k);
					
					if(f1.exit() == true){
						System.exit(0);
					}	
				}
			}
		}*/
	}

}
