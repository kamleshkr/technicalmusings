#Fundamental Data Types

#int and float
print (2 + 4)
print (type(6))
print (type(2 / 4))
print (type(1.5 + 1.5))
print (2 ** 3) #power 8
print (2 // 4) #rounded down to an integer 0
print (5 % 4) #modulo 1

#math functions
print(round(3.1)) #3
print(round(3.9)) #4
print(abs(-20)) #20

#Operator precedence
print (2 + 2 * 2)

#binary version of a number
print(bin(10))
print(int('0b1010', 2))

#variables - snake_case, case-sensitive
user_name = 'Kamlesh'
print(user_name)

i, j, k = 10, 20, 30
print (i) #10
print (j) #20
print (k) #30

#Augmented assignment operator
emp_salary = 1000
emp_salary += 2000
print(emp_salary)

#String
print(type('Kamlesh'))
multiline = '''
<pre>
this is a String
that spans multiple lines
Did Java copy Python :D
<pre>
'''
print(multiline)

year = 2025
tax = '30%'
print(f'Tax in year {year} is {tax}') #python3 feature | python2 has .format(val1, val2)