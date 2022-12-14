# [react-native-android-fragment-example](https://github.com/svnm/react-native-android-fragment-example)



## Running the example

This app extends the [integration-with-existing-apps guide](https://reactnative.dev/docs/integration-with-existing-apps). Visit this if you are having any issues in initial setup.

It follows the approach set up by [hudl](https://github.com/hudl/react-native-android-fragment) using a [ReactFragment](https://github.com/facebook/react-native/blob/master/ReactAndroid/src/main/java/com/facebook/react/ReactFragment.java) to render a React Native component into an Android Fragment.

Install dependencies:

```bash
yarn
```

Run the metro bundler:

```bash
yarn start
```

Run your android app

Open up Android studio and click ▶️


## Documentation

The React Native guide for [Integration with Existing Apps](https://reactnative.dev/docs/integration-with-existing-apps) details how to integrate a full-screen React Native app into an existing Android app as an Activity. To use React Native components within Fragments in an existing app requires some additional setup. The benefit of this is that it allows for a native app to integrate React Native components alongside native fragments in an Activity.

### 1. Add React Native to your app

Follow the React Native guide for [Integration with Existing Apps](https://reactnative.dev/docs/integration-with-existing-apps) until the Code integration section. Continue to follow Step 1. Create a index.android.js file and Step 2. Add your React Native code from this section. If you create the file as index.js you will need to override getJSMainModule() to `return "index";`

### 2. Integrating your App with a React Native Fragment

In this guide we no longer want a full screen React Native Activity, instead we want to render our React Native component into a Fragment. The component may be termed a "screen" or "fragment". It will be analagous to an Android fragment, and likely contains child components. In our example we placed all of these components in a `/fragments` folder and the child components used to compose the fragment were in a `/components` folder.

We need to implement ReactApplication in our main Application Java class. If you have created a new project from Android Studio with a default activity, you will need to create a new class e.g. MyReactApplication.java. If it is an existing class you can find this main class in your  `AndroidManifest.xml` file. Under the `<application />` tag you should see a property `android:name=".MyReactApplication"`. The value provided is the class you want to add this implementation to and provide the required methods.

Ensure your main Application Java class implements ReactApplication:

```java
public class MyReactApplication extends Application implements ReactApplication {...}
```

Override the required methods `getUseDeveloperSupport`, `getPackages` and `getReactNativeHost`:

```java
public class MyReactApplication extends Application implements ReactApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        protected List<ReactPackage> getPackages() {
            List<ReactPackage> packages = new PackageList(this).getPackages();
            // Packages that cannot be autolinked yet can be added manually here
            return packages;
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
```

If you are using Android Studio, use Alt + Enter to add all missing imports in your class. Alternatively these are the required imports to include manually:

```java
import android.app.Application;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.List;
```

Perform a "Sync Project files with Gradle" operation.

### Step 3. Add a FrameLayout for the React Native Fragment

We will now add the React Native Fragment to an Activity. For a new project this will be MainActivity but it can be added to any Activity and more fragments can be added to additional Activities as you integrate more React Native components into your app.

First add the React Native Fragment to our Activity's layout. For example main_activity.xml in the `res/layouts` folder.

Add a `<FrameLayout>` with an id, width and height. This is the layout we will find by id and render our React Native Fragment into.

```xml
<FrameLayout
    android:id="@+id/reactNativeFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Step 4. Add a React Native Fragment to the FrameLayout

To add your React Native Fragment to your layout we need to have an Activity. As mentioned in a new project this will be MainActivity. In this Activity we will add a button and an event listener, on button click we will render out React Native Fragment.

First modify your Activity layout to add the button:

```xml
<Button
    android:layout_margin="10dp"
    android:id="@+id/button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Show react fragment" />
```

Now back in our Activity class e.g. MainActivity.java we need to add an OnClickListener for the button, instantiate our ReactFragment and add the fragment to the frame layout.

Add to the top of our Activity the button field:

```java
private Button mButton;
```

Now update your Activity's onCreate method as follows:

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    mButton = findViewById(R.id.button);
    mButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Fragment reactNativeFragment = new ReactFragment.Builder()
                    .setComponentName("HelloWorld")
                    .setLaunchOptions(getLaunchOptions("test message"))
                    .build();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.reactNativeFragment, reactNativeFragment)
                    .commit();

        }
    });
}
```

In the code above `Fragment reactNativeFragment = new ReactFragment.Builder()` creates our ReactFragment. and `getSupportFragmentManager().beginTransaction().add()` will add the Fragment to our Frame Layout

If you are using a starter kit for React Native, replace the "HelloWorld" string with the one in your index.js or index.android.js file (it’s the first argument to the AppRegistry.registerComponent() method).

Add the `getLaunchOptions` method which will allow you to pass props through to your component. This is optional and your can remove `setLaunchOptions` if you don't need to pass any props.

```java
private Bundle getLaunchOptions(String message) {
    Bundle initialProperties = new Bundle();
    initialProperties.putString("message", message);
    return initialProperties;
}
```

Add all missing imports in your Activity class. Be careful to use your package’s BuildConfig and not the one from the facebook package! Alternatively these are the required imports to include manually:

```java
import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
```

Perform a "Sync Project files with Gradle" operation.

### Step 5. Test your integration

Make sure you run `yarn` to install your react-native dependencies and run `yarn native` to start the metro bundler. Run your android app in Android Studio and it should load the JavaScript code from the development server and display it in your React Native Fragment in the Activity.

### Step 6. Additional setup - Native modules

We may need to call out to existing Java code from our react component being used in the Fragment. Native modules will allow you to call to native code, allowing you to run methods in your native app. Follow the setup here [native-modules-android](https://reactnative.dev/docs/native-modules-android.html#content)
