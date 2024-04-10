<div align="center">

<a href="https://mihon.app">
    <img src="./.github/assets/logo.png" alt="Mihon logo" title="Mihon logo" width="80"/>
</a>

# Mihon [Bitmap.kt](#)

A Kotlin Multiplatform image manipulation library based on Android's Bitmap.

[![License: MPL-2.0](https://img.shields.io/github/license/mihonapp/bitmap.kt?labelColor=27303D&color=0877d2)](/LICENSE)
[![Discord server](https://img.shields.io/discord/1195734228319617024.svg?label=&labelColor=6A7EC2&color=7389D8&logo=discord&logoColor=FFFFFF)](https://discord.gg/mihon)

<div align="left">

### Usage
Add `jitpack.io` to your dependencies:
```build.gradle.kts
dependencies {
    maven("https://www.jitpack.io")
}
```

Add a dependency. Platform libraries (*-jvm, *-android) will be automatically included into the corresponding source sets.
> `initial_release` here is a tag. use `main-SNAPSHOT` to use the latest version from branch `main`, use `9a99a3c` to refer to a certain commit hash.
```build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.github.mihonapp.bitmap~kt:bitmap:initial_release")
        }
    }
}
```
</div>

### Repositories

[![mihonapp/mihon - GitHub](https://github-readme-stats.vercel.app/api/pin/?username=mihonapp&repo=mihon&bg_color=161B22&text_color=c9d1d9&title_color=0877d2&icon_color=0877d2&border_radius=8&hide_border=true)](https://github.com/mihonapp/mihon/)
[![mihonapp/website - GitHub](https://github-readme-stats.vercel.app/api/pin/?username=mihonapp&repo=website&bg_color=161B22&text_color=c9d1d9&title_color=0877d2&icon_color=0877d2&border_radius=8&hide_border=true)](https://github.com/mihonapp/website/)

### Credits

Thank you to all the people who have contributed!

<a href="https://github.com/mihonapp/bitmap.kt/graphs/contributors">
    <img src="https://contrib.rocks/image?repo=mihonapp/bitmap.kt" alt="Mihon bitmap.kt contributors" title="Mihon bitmap.kt contributors"/>
</a>

### License

<pre>
Copyright © 2024 The Mihon Open Source Project

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
</pre>

</div>
