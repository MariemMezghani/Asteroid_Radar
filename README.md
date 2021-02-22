# Overview:

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, 
you can view all the detected asteroids given a period of time with data such as the size, velocity, distance to earth and if they are potentially hazardous. 
The resulting output of the project is two screens: a Main screen with a list of all the detected asteroids and a Details screen that displays the data of that asteroid once 
it´s selected in the Main screen list. The main screen shows the NASA image of the day to make the app more striking.

# Screenshots:

![image](https://user-images.githubusercontent.com/35550711/108706920-9f26a000-750f-11eb-8a57-1057f26f2d6a.png)
![image](https://user-images.githubusercontent.com/35550711/108701904-d776b000-7508-11eb-9755-5d97d8f3b7dc.png)
![image](https://user-images.githubusercontent.com/35550711/108702129-1dcc0f00-7509-11eb-856d-de302c775566.png)
![image](https://user-images.githubusercontent.com/35550711/108706766-5d95f500-750f-11eb-81db-2f76eb2ea5ba.png)

# Libraries:

- Retrofit library to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in the form of custom classes.
- Picasso library to download and cache image.

# Built With

- AndroidX Room
- AndroidX Navigation
- KotlinX Coroutines
- AndroidX ViewModel 
- AndroidX RecyclerView

# License

Copyright 2021 Mariem Mezghani

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
