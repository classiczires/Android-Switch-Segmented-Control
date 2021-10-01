

# Switch Segmented Control for android
[![](https://jitpack.io/v/classiczires/Android-Switch-Segmented-Control.svg)](https://jitpack.io/#classiczires/Android-Switch-Segmented-Control)
[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)

Android Switch Segmented Control is an implementation of iOS Segmented Control for use in Android applications. It runs on API 17+ (Android 4.2)
# Android segmented control style
<img src="ZiresSwitchSegmentedControl.png" width="50%">
<img src="androidswitchsegmentedcontrol.gif" width="32%" >



## Installation

### Gradle
Add this to the root build.gradle at the end of repositories (**WARNING:** Make sure you add this under **allprojects** not under buildscript):
```Gradle
allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
```

Add the dependency to the project build.gradle:
```Gradle
dependencies {
	        implementation 'com.github.classiczires:Android-Switch-Segmented-Control:1.0.4'
}
```

## Usage

  1. First add a ZiresSwitchSegmentedControl to your xml layout as :
```xml
    <com.zires.switchsegmentedcontrol.ZiresSwitchSegmentedControl
        android:id="@+id/zires_switch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:activeBgColor="@color/green"
        app:activeTextColor="@android:color/white"
        app:backgroundColor="@android:color/white"
        app:borderColor="@color/green"
        app:inactiveTextColor="@android:color/darker_gray"
        app:switchFontFamily="serif-monospace"
        app:checked="true"
        app:cornerRadius="50dp"
        app:strokeWidth="2dp"
        app:textSize="48sp"
        app:textToggleLeft="OFF"
        app:textToggleRight="ON" />
```
        
  2. To handle switch state in your Activity/Fragment class set a ToggleSwitchChangeListener to it as below:
```kotlin
        val switchSegmentedControl: ZiresSwitchSegmentedControl = findViewById(R.id.zires_switch)
        switchSegmentedControl.setOnToggleSwitchChangeListener(object : 
            ZiresSwitchSegmentedControl.OnSwitchChangeListener {
            override fun onToggleSwitchChangeListener(isChecked: Boolean) {
                // your code
            }
        })
 ```
 
 3. To check and uncheck use this method:
 ```kotlin
        switchSegmentedControl.setChecked(true)
 ```
# License
    Copyright 2021 Saeed Karimi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

