public class MergeSort{
	public static int [] getArray(int size){
		return new int [size];
	}
	//include start exclude end
	public static int [] resize(int [] arr, int start, int end){
		if(start >= 0 && end <= arr.length){
			int [] temp = new int [end-start];
			for(int i = 0; i < temp.length; i++){
				temp[i] = arr[start+i];
			}
			return temp;
		}
		return null;
	}
	public static int [] doubleMerge(int [] arr){
		if(arr.length >= 2){
			int [] temp1 = resize(arr,0, arr.length/2), temp2 = resize(arr, arr.length/2, arr.length);
			temp1 = doubleMerge(temp1);
			temp2 = doubleMerge(temp2);
			for(int i = 0, h1 = 0, h2 = 0; i < arr.length; i++){
				if(h1 < temp1.length && h2 < temp2.length){
					if(temp1[h1] < temp2[h2]){
						arr[i] = temp1[h1];
						h1++;
					}else{
						arr[i] = temp2[h2];
						h2++;
					}
				}else{
					if(h1 == temp1.length){
						arr[i] = temp2[h2];
						h2++;
					}else{
						arr[i] = temp1[h1];
						h1++;
					}
				}
			}
		}
		return arr;
	}
	public static int [] tripleMerge(int [] arr){
		return null;
	}
	public static void print(int [] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	}
	public static void main(String [] args){
		int [] arr = {3,7,9,2,1,5,3,5,1,3,7,6,3};
		arr = doubleMerge(arr);
		print(arr);
	}
}