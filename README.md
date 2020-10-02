# ElegantSeekBar
An elegant, highly customizable custom 'circular' seekbar Android Library.
[![](https://jitpack.io/v/yashprakash13/ElegantSeekBar.svg)](https://jitpack.io/#yashprakash13/ElegantSeekBar)

### Looks like this:
![Screenshot_20190330-213258](https://user-images.githubusercontent.com/34805906/55278906-72c07400-5338-11e9-89f9-ec6bc7f3ad11.png)
![Screenshot_20190330-214412](https://user-images.githubusercontent.com/34805906/55278907-7522ce00-5338-11e9-8ace-93eb216341f5.png)
![Screenshot_20190330-213437](https://user-images.githubusercontent.com/34805906/55278910-77852800-5338-11e9-8698-f5fb5236ad3f.png)

### Add it to your project!
Step 1. Add the JitPack repository to your build file
* Add it in your root build.gradle at the end of repositories:
```gradle
      allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	} 
```
Step 2. Add the dependency in app level gradle:
```
  dependencies {
	        implementation 'com.github.yashprakash13:ElegantSeekBar:v1.0'
	}
```
### Add using XML:
```xml
<tech.pcreate.elegantseekbarlibrary.ElegantSeekbar
        android:id="@+id/elegantSeekBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        app:maxRange="30"
        app:text_size="33sp"
        app:smallerTextContent="Units"
        app:text_color="@color/colorPrimaryDark"
        app:smaller_text_size="23sp"
        app:circle_thickness="15dp"
        app:smaller_text_color="@color/colorAccent"
        app:circle_button_1_color="@color/colorAccent"
        app:circle_button_2_color="@color/colorPrimaryDark"
        app:line_color="@android:color/holo_blue_dark"
        app:base_circle_paint="@android:color/darker_gray"/>
```
### Or using Java:
```java
ElegantSeekbar elegantSeekbar = findViewById(R.id.elegantSeekBar);

// Progress Listener can be added like this:
elegantSeekbar.setOnProgressChangedListener(new ElegantSeekbar.OnProgressChangedListener() {
            @Override
            public void startValue(String start) {
                //do something
            }

            @Override
            public void endValue(String end) {
                //do something
            }
});
```
### Other methods for you to customize:
```java
elegantSeekbar.setTextSizes(int textSize, int smallerTextSize)
elegantSeekbar.setTextColors(int textColor, int smallerTextColor)
elegantSeekbar.setLineColor(int color)
elegantSeekbar.setBaseCircleColor(int color)
elegantSeekbar.setCircleButtonColors(int color1, int color2)
elegantSeekbar.setMaxRange(int maxRange)
elegantSeekbar.setSmallerTextContent(String text)
elegantSeekbar.setCircleThickness(int thickness)
elegantSeekbar.setSize(int pixels) //specify pixels if you need to use a size other than the default size of the seekbar
```
### Sample App
[Sample app](https://github.com/yashprakash13/ElegantSeekBar/tree/master/app) included for your reference!

### Contribute
Feel free to open a pull request to improve this library. :)

### Like this library? Please show your support!
<b>Buy me a Coffee!</b> 
<a href='https://ko-fi.com/G2G3R125' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi3.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>



