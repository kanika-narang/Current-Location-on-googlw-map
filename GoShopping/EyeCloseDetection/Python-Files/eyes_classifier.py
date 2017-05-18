#to create model.svm
import cv2
import numpy as np
import pickle
import os
from sklearn import svm

def loadImages(path):
	name  = os.listdir(path)
	imgs=[]
	for x in name:
		x=path+"//"+x
		tmp = cv2.imread(x,0)
		tmp = cv2.resize(tmp,(100,100))
		img = np.array(tmp).ravel()
		print(len(img))
		imgs.append(np.float32(img))
		print(len(imgs))
	return imgs

def putLabel(num,label):
	print([label]*num)
	return [label]*num

def combineLists(*args):
	tmpList = []
	for i in range(0,len(args)):
		tmpList = tmpList + args[i]
	return tmpList

pos = loadImages("../images/eyes/negative/")
posL = putLabel(len(pos),0)  ## Label 0 for closed eyes
neg = loadImages("../images/eyes/positive/")
negL = putLabel(len(neg),1) ## label 1 for open eyes
data = combineLists(pos,neg)
label = combineLists(posL,negL)
if not(os.path.exists("model.svm")):
	tmp = svm.LinearSVC()
	tmp.fit(data,label)
	with open('model.svm', 'wb') as f:
		pickle.dump(tmp, f)
else:
	with open('model.svm', 'rb') as f:
		tmp = pickle.load(f)	

test = loadImages("../images/dataset_eyes/test_eyes")
op = tmp.predict(test)
print op
