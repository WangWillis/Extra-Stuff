#include <iostream>
#include <string>

using namespace std;

void allBytes(string byte, int size){
	if(size < byte.length())
		byte[size] = '0';
	for(int i = size - 1; i >= 0; i--){
		allBytes(byte, i);
	}
	cout << byte << endl;
	byte[size] = '1';
}

int main(){
	string byte = "1111";
	allBytes(byte, 4);
	return 0;
}