#image vedio eye detection
import numpy as np
import cv2
import os
import pickle
from sklearn import svm
# import MobileDetect as mb
import sys
import web
import json
import requests

#import winsound
def loadModel(modelName, mode):
    with open(modelName, mode) as f:
            tmp = pickle.load(f)
    return tmp
        
def initialFaceDetection(cap,face_cascade):
        while 1:
                ret,img= cap.read()
                resized_img=cv2.resize(img,(900,600),interpolation = cv2.INTER_CUBIC)
                gray = cv2.cvtColor(resized_img, cv2.COLOR_BGR2GRAY)
                faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2,minNeighbors=2,minSize=(150,150),flags=cv2.cv.CV_HAAR_DO_CANNY_PRUNING)
                if(faces==()):
                        print("No face detected...")
                else:
                        x=faces[0][0]
                        y=faces[0][1]
                        w= faces[0][2]
                        h= faces[0][3]
                        return x,y,w,h,resized_img
                

face_cascade = cv2.CascadeClassifier('../haarCascade/haarcascade_frontalface_default.xml')
eye_cascade = cv2.CascadeClassifier('../haarCascade/haarcascade_eye_tree_eyeglasses.xml')

tmp= loadModel('model.svm', 'rb')
count=0;
x=0
y=0
w=0
h=0

cap= cv2.VideoCapture(0)

x,y,w,h,resized_img=initialFaceDetection(cap,face_cascade)

roi = resized_img[y:y+h, x:x+w]
hsv_roi =  cv2.cvtColor(roi, cv2.COLOR_BGR2HSV)
mask = cv2.inRange(hsv_roi, np.array((0., 60.,32.)), np.array((180.,255.,255.)))
roi_hist = cv2.calcHist([hsv_roi],[0],mask,[180],[0,180])
cv2.normalize(roi_hist,roi_hist,0,255,cv2.NORM_MINMAX)
term_crit = ( cv2.TERM_CRITERIA_EPS | cv2.TERM_CRITERIA_COUNT, 10, 1 )
count=1000
countOpenEye=0
countCloseEye=0
Freq = 2500 # Set Frequency To 2500 Hertz
Dur = 1000 # Set Duration To 1000 ms == 1 second
countFrames=0
warning=0
while 1:
        ret,img= cap.read()
        if ret==True:
                resized_img=cv2.resize(img,(900,600),interpolation = cv2.INTER_CUBIC)
                hsv = cv2.cvtColor(resized_img, cv2.COLOR_BGR2HSV)
                dst = cv2.calcBackProject([hsv],[0],roi_hist,[0,180],1)
                #ret, (x,y,w,h) = cv2.CamShift(dst, (x,y,w,h), term_crit)
                x,y,w,h,resized_img=initialFaceDetection(cap,face_cascade)
                #mb.predictClass(img,x,y,w,h)
                cv2.rectangle(resized_img,(x,y),(x+w,y+h),(255,0,0),2)
                roi_color= resized_img[y:(y+2*h/3), x:(x+w)]
                roi_gray= cv2.cvtColor(roi_color, cv2.COLOR_BGR2GRAY)
                roi_gray_equalized=cv2.equalizeHist(roi_gray)
                eyes = eye_cascade.detectMultiScale(roi_gray_equalized)
                for (ex,ey,ew,eh) in eyes:
                        roi_eyes= roi_gray_equalized[ey:(ey+eh),ex:(ex+ew)]
                        resized_roi_eyes= cv2.resize(roi_eyes,(100,100))
                        test= np.array(resized_roi_eyes).ravel()
                        op= tmp.predict([test])
                        countFrames=countFrames+1
                        if(op==0):
                            print(0)
                            cv2.rectangle(roi_color,(ex,ey),(ex+ew,ey+eh),(0,0,255),2)
                            countCloseEye=countCloseEye+1
                            
                        else:
                            print(1)
                            cv2.rectangle(roi_color,(ex,ey),(ex+ew,ey+eh),(0,255,0),2)
                            countOpenEye=countOpenEye+1
                        if (countFrames%15==0):
                            if(countCloseEye>=10 and countOpenEye<=4):
                                print('Driver is Sleeping.....')
                                cmd='aplay beep-01a.wav'
                                os.system(cmd)
                                warning=warning+1;
                                #sys.stdout.write('\a')
                                #sys.stdout.flush()
                                if(warning>=3):
                                    for i in range(1,3):
                                        cmd='aplay beep-01a.wav'
                                        os.system(cmd)
                                    print('Sleeping')
                                    url='http://localhost:8080/showWindow'
                                    send_url = 'http://freegeoip.net/json'
                                    r = requests.get(send_url)
                                    j = json.loads(r.text)
                                    lat = str(j['latitude'])
                                    lon = str(j['longitude'])
                                    loc=lat+", "+lon
                                    data = {"driverName" : "XYZ","Cab_No":"MH21-1342","Latitude":lat,"Longitude":lon,"address":loc}
                                    data_json = json.dumps(data)
                                    headers = {'Content-type': 'application/json'}
                                    response = requests.post(url, data=data_json, headers=headers)
                                    cap.release
                                    break;

                            countOpenEye=0
                            countCloseEye=0
                            #winsound.Beep(Freq,Dur)

                           
                cv2.imshow('img',resized_img)
                k=cv2.waitKey(1)
                if k==27:
                    break;
