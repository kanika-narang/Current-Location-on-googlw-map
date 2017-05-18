

## This program extract samples of eyes extracted and store those...

import numpy as np
import cv2
import os
import pickle
from sklearn import svm
face_cascade = cv2.CascadeClassifier('../haarCascade/haarcascade_frontalface_default.xml')
eye_cascade = cv2.CascadeClassifier('../haarCascade/haarcascade_eye_tree_eyeglasses.xml')

train_path = "../OpenEye"
training_names = os.listdir(train_path)
image_paths=[]
for training_name in training_names:
    dir = os.path.join(train_path, training_name)
    image_paths.append(dir)

with open('model.svm', 'rb') as f:
    tmp = pickle.load(f)	
count=0;
x=0
y=0
w=0
h=0
count=298
for path in image_paths:
        img = cv2.imread(path) 
        resized_img=cv2.resize(img,(900,600),interpolation = cv2.INTER_CUBIC)
        gray = cv2.cvtColor(resized_img, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2,minNeighbors=2,minSize=(150,150),flags=cv2.cv.CV_HAAR_DO_CANNY_PRUNING)
        if(faces==()):
                print("No face detected...")
        else:
                print("Face detected...")
                x=faces[0][0]
                y=faces[0][1]
                w= faces[0][2]
                h= faces[0][3]
                face = resized_img[y:y+h, x:x+w]
                cv2.rectangle(resized_img,(x,y),(x+w,y+h),(255,0,0),2)
                eyes = eye_cascade.detectMultiScale(face)
                for (ex,ey,ew,eh) in eyes:
                        eyesRoi= face[ey:ey+eh,ex:ex+ew]
                        resized_roi_eyes_color=cv2.resize(eyesRoi,(100,100))
                        cv2.imwrite("../images/eyes/positive/"+str(count)+"eye.jpg",resized_roi_eyes_color) # write eyes
                        count=count+1
