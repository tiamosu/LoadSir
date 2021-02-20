@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 15
    const val targetSdkVersion = 30

    const val versionName = "1.0"
    const val versionCode = 1
}

object Versions {
    const val kotlin = "1.4.21"
    const val appcompat = "1.2.0"
    const val constraintlayout = "2.0.4"
}

object Publish {
    const val userOrg = "weixia" //bintray.com用户名
    const val groupId = "me.tiamosu" //jcenter上的路径
    const val publishVersion = "2.0.5" //版本号
    const val desc = "Oh hi, this is a nice description for a project, right?"
    const val website = "https://github.com/tiamosu/LoadSir"
    const val gitUrl = "https://github.com/tiamosu/LoadSir.git"
    const val email = "djy2009wenbi@gmail.com"
    const val projectName = "X-LoadSir"
}

object Deps {
    //androidx
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidx_constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}