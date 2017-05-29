<h1>Basic android app using firebase as a backend service</h1>
<br/>
<strong>Step 1</strong> -<br/> 
	Create a project in firebase console.<br/>
	Add firebase to android app<br/> 
	Register app - Download config file - Move the json file in app directory of the app in android studio.<br/> 
	Add dependencies to your app<br/> 
		1.Project-level build.gradle (<project>/build.gradle):<br/> 
			// Add this line<br/> 
		    classpath 'com.google.gms:google-services:3.1.0'<br/> 
		2.App-level build.gradle (<project>/<app-module>/build.gradle):<br/> 
			// Add to the bottom of the file<br/> 
			apply plugin: 'com.google.gms.google-services'<br/> 
		3.Sync the project<br/> 
Step 2 - <br/> 
	Add the libraries for various firebase features.<br/> 
	Here, I am gonna add the gradle dependency line for realtime database<br/> 
	// Add the following <br/> 
	compile 'com.google.firebase:firebase-database:10.2.6'<br/> 
	<a target="_blank" href="https://firebase.google.com/docs/android/setup">Link for firebase documentation <a /><br/> 
