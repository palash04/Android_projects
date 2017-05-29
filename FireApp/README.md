#Basic android app using firebase as a backend service

Step 1 - 
	Create a project in firebase console.
	Add firebase to android app
	Register app - Download config file - Move the json file in app directory of the app in android studio.
	Add dependencies to your app
		1.Project-level build.gradle (<project>/build.gradle):
			// Add this line
		    classpath 'com.google.gms:google-services:3.1.0'
		2.App-level build.gradle (<project>/<app-module>/build.gradle):
			// Add to the bottom of the file
			apply plugin: 'com.google.gms.google-services'
		3.Sync the project
Step 2 - 
	Add the libraries for various firebase features.
	Here, I am gonna add the gradle dependency line for realtime database
	// Add the following 
	compile 'com.google.firebase:firebase-database:10.2.6'
	<a href="https://firebase.google.com/docs/android/setup">Link for firebase documentation <a />
