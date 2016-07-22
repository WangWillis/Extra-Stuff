def countPics(dirStr):
	stack = [0]
	spaces = 0
	count = 0
	totalCount = 0
	i = 0
	while(i < len(dirStr)):
		count += 1
		if(dirStr[i] == '\n'):
				count -= 1
				i += 1
				temp = 0
				while(i < len(dirStr) and dirStr[i] == ' '):
					temp += 1
					i += 1
				if(temp <= spaces):
					for j in range(0, spaces-temp):
						stack.pop()
					count = stack[-1]
				elif(temp > spaces):
					stack.append(count)
				spaces = temp
		elif(dirStr[i] == '.'):
			temp = i + 1
			while(i < len(dirStr) and dirStr[i] != '\n'):
				i += 1
			if(checkPic(dirStr[temp:i])):
				totalCount += count + i - temp
			count = stack[-1]
		else:
			i += 1
	return totalCount

def checkPic(fileStr):
	if(fileStr == "png" or fileStr == "jpg" or fileStr == "jif"):
		return True
	return False

def main():
	print "dir1\n dir2\n  hi.jpg\n  boo.txt\n dir3\ndir4\n dir5\n  dir6\n tea.png"
	print countPics("dir1\n dir2\n  hi.jpg\n  boo.txt\n dir3\ndir4\n dir5\n  dir6\n tea.png")

main()