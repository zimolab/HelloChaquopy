plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    /**
     * Apply the Chaquopy plugin
     *
     * See: https://chaquo.com/chaquopy/doc/current/android.html#android-plugin
     */
    id("com.chaquo.python")
}

android {
    namespace = "com.zimolab.demo.hellochaquopy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zimolab.demo.hellochaquopy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ndk {
            /**
             * See: https://chaquo.com/chaquopy/doc/current/android.html#android-abis
             *
             * The Python interpreter is a native component, so you must use the abiFilters setting
             * to specify which ABIs you want the app to support. The currently available ABIs are:
             *
             * - armeabi-v7a for older Android devices (Python 3.11 and older only)
             * - arm64-v8a for current Android devices, and emulators on Apple silicon
             * - x86 for older emulators (Python 3.11 and older only)
             * - x86_64 for current emulators
             *
             * Each ABI will add several MB to the size of the app, plus the size of any native
             * requirements.
             */
             abiFilters.add("arm64-v8a")
             abiFilters.add("armeabi-v7a")
             abiFilters.add("x86")
             abiFilters.add("x86_64")
             
        }

        flavorDimensions += "pyVersion"
        productFlavors {
             create("py38") { dimension = "pyVersion" }
        }
        

        /**
        * chaquopy setup
        *
        * See: https://chaquo.com/chaquopy/doc/current/android.html#chaquopy-block
        */
        chaquopy {
            productFlavors {
                 getByName("py38") { version = "3.8" }
            }
            

            defaultConfig {
                /**
                * Set python version
                *
                * See: https://chaquo.com/chaquopy/doc/current/android.html#python-version
                */
                version = "3.8"

                /**
                * Set the python command(or path to a python executable)
                *
                * See: https://chaquo.com/chaquopy/doc/current/android.html#buildpython
                */
                 buildPython("/path/to/python")

                /**
                * Install python packages
                *
                * See：https://chaquo.com/chaquopy/doc/current/android.html#android-requirements
                */
                pip {
                    // A requirement specifier, with or without a version number:
                    // install("scipy")
                    // install("requests==2.24.0")

                    // An sdist or wheel filename, relative to the project directory:
                    // install("MyPackage-1.2.3-py2.py3-none-any.whl")

                    // A directory containing a setup.py, relative to the project
                    // directory (must contain at least one slash):
                    // install("./MyPackage")

                    // "-r"` followed by a requirements filename, relative to the
                    // project directory:
                    // install("-r", "requirements.txt")

                    // To pass options to pip install, give them as a comma-separated list to the
                    // options method. For example:
                    // options("--extra-index-url", "https://example.com/private/repository")
                    // install("MyPackage==1.2.3")

                }

                /**
                 * The static proxy feature allows a Python class to extend a Java class, or to be
                 * referenced directly in Java code or the AndroidManifest.xml file without going
                 * through the Java API.
                 *
                 * To use this feature, write your Python classes using the syntax described in the
                 * “Static proxy”(https://chaquo.com/chaquopy/doc/current/python.html#static-proxy),
                 * then declare the containing modules as follows.
                 *
                 * Note:
                 * This feature requires Python on the build machine, which can be configured with
                 * the buildPython setting.
                 *
                 * See: https://chaquo.com/chaquopy/doc/current/android.html#static-proxy-generator
                 */
                // staticProxy("module.one", "module.two")

                /**
                 * At runtime, Python modules are usually loaded directly from the APK, and don’t
                 * exist as separate files. If there are certain packages in your source code or
                 * requirements which need to exist as separate files, you can declare them as below.
                 *
                 * Note:
                 * Each extracted file will slightly slow down your app’s startup, so this setting
                 * should be used on the deepest possible package.
                 *
                 * See: https://chaquo.com/chaquopy/doc/current/android.html#extractpackages
                 */
                // extractPackages("package1", "package2.subpkg")

                /**
                 * Your app will start up faster if its Python code is compiled to .pyc format, \
                 * so this is enabled by default.
                 *
                 * If bytecode compilation succeeds, the original .py files will not be included in
                 * the APK, unless they’re covered by the extractPackages setting. However, this
                 * prevents source code text from appearing in stack traces, so during development
                 * you may wish to disable it.
                 *
                 * There are individual settings for:
                 *
                 * - src: local source code
                 * - pip: requirements
                 * - stdlib: the Python standard library
                 *
                 * In the case of src and pip, your buildPython must use the same bytecode format as
                 * Chaquopy itself. Usually this means it must have the same minor version,
                 * e.g. if your app’s Python version is 3.8, then buildPython can be any version of
                 * Python 3.8.
                 *
                 * If bytecode compilation fails, the build will continue with a warning, unless
                 * you’ve explicitly set one of the pyc settings to true. Your app will still work,
                 * but its code will have to be compiled on the target device, which means it will
                 * start up slower and use more storage space.
                 *
                 * Note: This feature requires Python on the build machine, which can be configured
                 * with the buildPython setting.
                 *
                 * See: https://chaquo.com/chaquopy/doc/current/android.html#android-bytecode
                 */
                pyc {
                    
                }
            }

            //
            sourceSets {
                /**
                 * By default, Chaquopy will look for Python source code in the python subdirectory
                 * of each source set. This means the default Python source directory is
                 * src/main/python.
                 *
                 * To include Python code from other directories, use the chaquopy.sourceSets block.
                 * For example:
                 *
                 * getByName("main") {
                 *     srcDir("some/other/dir")
                 * }
                 *
                 * See: https://chaquo.com/chaquopy/doc/current/android.html#android-source
                 */
                 
            }
        }


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}