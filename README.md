# AnyMaps

AnyMaps allows you to use the same API for different maps providers without the need to adjust existing Google Maps implementation (apart from changing the package name).

We are relying on this library in [car2go](https://www.car2go.com) to allow customers without Google Play Services to use our product.

## Features

* Does not depend on any map provider - you can even use it without Google Maps.
* Map provider can be changed at runtime.
* Easy to extend. New providers can be added without changing the existing code.
* Map providers supported out of the box: Google Maps, OpenStreet maps, Baidu maps.

## Add it to your project

### Step one

Add `jitpack.io` to list of your repositories.

```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
```

### Step two

Choose which kinds of maps you want to use and add the latest version to your project.

```groovy
// Required for all projects
compile 'com.github.car2go.AnyMaps:anymaps-base:x.y.z'
 
// [Optional] Google Maps
compile 'com.github.car2go.AnyMaps:anymaps-google:x.y.z'
compile 'com.google.android.gms:play-services-maps:x.y.z'
 
// [Optional] OpenStreet maps
compile 'com.github.car2go.AnyMaps:anymaps-osm:x.y.z'
 
// [Optional] Baidu maps
compile 'com.github.car2go.AnyMaps:anymaps-baidu:x.y.z'
```

Depending on the maps which you choose you will need to add API keys to your AndroidManifest (the same way as you would do it without using AnyMaps). For example, for Google Maps it would be:

```xml
<application>
    
    <meta-data 
        android:name="com.google.android.geo.API_KEY" 
        android:value="Your API key"/>
    
</application>
```

### Step three

Add view to your layout.

```xml
<!-- There is a MapView implementation for each kind of Map. This example shows Google Maps. -->
<com.car2go.maps.google.MapView
        android:id="@+id/map_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

And then call `onCreate`, `onResume`, `onPause`, `onSaveInstanceState`, `onDestroy` and `onLowMemory` in your Activity. Check the example app for more details.

And you are good to go!

## About

Lead Maintainer: [Dmitry Zaitsev](https://github.com/dmitry-zaitsev)

## License

```
Copyright 2017 car2go group GmbH

Released under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
