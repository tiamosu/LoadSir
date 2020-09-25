@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    const val versionName = "1.0"
    const val versionCode = 1
}

object Versions {
    const val kotlin = "1.4.10"
    const val lifecycle = "2.2.0"
    const val appcompat = "1.2.0"
    const val viewpager2 = "1.0.0"
    const val constraintlayout = "2.0.1"
    const val corektx = "1.3.1"
    const val fragmentktx = "1.2.5"
    const val navigation = "2.3.0"
    const val leakcanary = "2.4"
}

object Publish {
    const val userOrg = "weixia" //bintray.com用户名
    const val groupId = "me.tiamosu" //jcenter上的路径
    const val publishVersion = "2.0.2" //版本号
    const val desc = "Oh hi, this is a nice description for a project, right?"
    const val website = "https://github.com/tiamosu/LoadSir"
    const val gitUrl = "https://github.com/tiamosu/LoadSir.git"
    const val email = "djy2009wenbi@gmail.com"
}

object Deps {
    //androidx
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidx_viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val androidx_constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.corektx}"

    //navigation
    const val androidx_fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragmentktx}"
    const val androidx_navigation_runtime =
        "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val androidx_navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val androidx_navigation_ui_ktx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //lifecycle
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycle_common_java8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    //kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlin_stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    //leakcanary
    const val leakcanary_android =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
}