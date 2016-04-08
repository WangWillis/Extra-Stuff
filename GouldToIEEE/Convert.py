import math

def getBinary(hexa, bits):
	return bin(int(hexa, 16))[2:].zfill(bits)

def valueToBin(value):
	return bin(value)[2:]

def getHex(binary):
	return hex(int(binary, 2))

def getInt(binary):
	return int(binary, 2)

def getValueGould(binary):
	sign = 0
	exp = 0
	m = 0
	sign = int(binary[0])
	exp = getInt(binary[1:-24])
	m = getInt(binary[8:])
	return math.pow(-1, sign)*math.pow(16, exp-64)*(m/(math.pow(2,24)))

def valueToIEEE(value):
	v = abs(value)
	s = 1
	if v == value:
		s = 0
	exp = int(math.log(v, 2)+127)
	if exp < 0:
		exp = 0
	m = ''
	if exp != 255 and exp != 0:
		m = int(((v/(math.pow(2, exp-127)))-1)*math.pow(2, 23))
		m = valueToBin(m)
	for i in range(0,23-len(m)):
		m = '0' + m
	exp = valueToBin(exp)
	for i in range(0, 8-len(exp)):
		exp += '0'
	return valueToBin(s) + exp + m

def main():
	case = '88888888'
	binary = getBinary(case, 32)
	value = getValueGould(binary)
	print(getHex(valueToIEEE(value))[:10])

main()