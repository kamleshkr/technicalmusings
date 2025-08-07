#Create a dictionary
studentDict = {
    'name': 'Kamlesh',
    'age': 'well..',
    'hobby': 'learning'
}

studentDict2 = dict(name='Kamlesh', age=':)', hobby='learning')

print(studentDict)
print(studentDict2)

#Read data from dictionary
print(studentDict['name'])
print(studentDict.get('name'))
print(studentDict.get('grade'))
print(studentDict.get('grade', 30))

#Remove Item
studentDict2.clear()
print(studentDict2)

print(studentDict.pop('age'))
print(studentDict)

#Copy
studentDict2 = studentDict.copy()
print(studentDict2)

#Add
studentDict.update(age=300) #For my son I am 300 years old :)
print(studentDict)


