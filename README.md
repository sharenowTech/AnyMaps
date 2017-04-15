# AnyMaps

AnyMaps allows you to use the same API for different maps providers without the need to adjust existing Google Maps implementation (apart from changing the package name).

We are relying on this library in [car2go](https://www.car2go.com) to allow customers without Google Play Services to use our product.

### Features

* Does not depend on any map provider - you can even use it without Google Maps.
* Map provider can be changed at runtime.
* Extendible - new providers can be added without changing the existing code.
* Map providers supported out of the box: Google Maps, OpenStreet maps, Baidu maps.

### Add it to your project

#### Step one

Add `jitpack.io` to list of your repositories.

```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
```

#### Step two

Choose which kinds of maps you want to use and add the latest version to your project.

```groovy
// Required for all projects
com.github.car2go.AnyMaps:anymaps-base:x.y.z

// [Optional] Google Maps
com.github.car2go.AnyMaps:anymaps-google:x.y.z

// [Optional] OpenStreet maps
com.github.car2go.AnyMaps:anymaps-osm:x.y.z

// [Optional] Baidu maps
com.github.car2go.AnyMaps:anymaps-baidu:x.y.z
```

### About

Lead Maintainer: [Dmitry Zaitsev](https://github.com/dmitry-zaitsev)

### License

```
Copyright 2017 car2go group GmbH

Released under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
