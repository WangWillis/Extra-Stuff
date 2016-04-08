void reverse(char* str){
	int len = strlen(str);
	for(int i = 0; i < len/2, i++){
		char temp = str[i];
		str[i] = str[len - i - 1];
		str[len - i - 1] = temp;
	}
}

string comp(string str){
	string temp = "";
	int count = 1;
	char prv = str[0];
	for(int i = 1; i < str.length(); i++){
		if(prv == str[i]){
			count++;
		}else{
			temp += prv + (char)count;
			count = 1;
			prv = str[i];
		}	
	}
	if(temp.length() < str.length()){
		return temp;
	}else{
		return str;
	}
}

LL aX(LL stream, int x){
	LL temp;
	Queue<int> stm;
	stream.toQueue(stm);
	while(!stm.isEmpty()){
		int num = stm.pop();
		if(num >= x){
			temp.add(x);
		}else{
			temp.addToFront(x);
		}
	}
	return temp;
}

int g(int n){
	if(n <= 3){
		return n;
	}else{
		return g(n-1) + 2*g(n-2) + 3*g(n-3);
	}
}

1*(-1)+5+8