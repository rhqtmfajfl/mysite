
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcdefg";
		
		String dog = "dog.jpg";
		
		System.out.println(str.lastIndexOf("c"));
	
		System.out.println(dog.lastIndexOf('.')+1); // 위치는 3인데 +1이니까 4가 나온다.
		System.out.println(dog.lastIndexOf('.'));  //여기는 위치로인데 3이다.
		
		System.out.println("substring : "+dog.substring(dog.lastIndexOf('.')+1));  //4부터 마지막 까지
		/*
		 * substring 
		 * 
		 * substring(시작위치)
		 * substring(시작위치, 끝위치)
		 * 
		 * 주의
		 * 시작위치부터 끝위치 전까지 이다
		 * 인덱스는 0부터 시작
		 */
	}

}
