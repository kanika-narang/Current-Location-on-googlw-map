#!/usr/bin/env python
from Tkinter import Tk, Label, Button
import web
import json
from geopy.geocoders import Nominatim

urls = (
 '/saveDetails','save_details',
 '/saveAudio','save_audio',
 '/showWindow','show_window'
)

app = web.application(urls, globals())

class save_details:
 def GET(self):
     print("here")
     data=web.data()
     print(data)
     dataJson=json.loads(web.data())
     value = dataJson["name"]
     print(value)
     return " Thanks " + value + ".."

     #return "success"
class show_window:
 def POST(self):
     print("here")
     data=web.data()
     print(data)
     dataJson=json.loads(web.data())
     value = dataJson["driverName"]
     add=dataJson["address"]
     #find location
     # geolocator = Nominatim()
     # #location = geolocator.reverse("52.509669, 13.376294")
     # location = geolocator.reverse(add)
     # print(location.address)
     window = Tk()
     window.title("Driver Change Request Window..")
     window.geometry("500x500")
     my_gui = MyFirstGUI(window)
     window.mainloop()
     # data=web.data()
     # print(data)
     # dataJson=json.loads(web.data())
     # value = dataJson["name"]
     # print(value)
     # return " Thanks " + value + ".."

     #return "success"

class save_audio:
 def POST(self):
     data=web.data()
     print(data)
     return "success" 

class MyFirstGUI:
    def __init__(self, master):
        self.master = master
        master.title("A simple GUI")

        self.label = Label(master, text="Driver Change Request Window..")
        self.label.pack()

        self.label = Label(master, text="Driver Name: Monika Mb")
        self.label.pack()

        self.label = Label(master, text="Vehical No:MH21-1342")
        self.label.pack()

        self.greet_button = Button(master, text="Allocate New cab", command=self.allocate)
        self.greet_button.pack()

        self.close_button = Button(master, text="Send Driver Details", command=self.sendMail)
        self.close_button.pack()

    def allocate(self):
        print("New Cab Allocated")
    def sendMail(self):
        print("Mail Sent..")



if __name__ == "__main__":
 app.run()
