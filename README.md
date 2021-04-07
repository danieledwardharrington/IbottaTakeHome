# Overview

I'm using Kotlin here along with a variety of tools and libraries. I'll list them below along with some general notes/comments about the assessment. I targeted the latest version of Android.

# Libraries/Technologies

* Room - I'm using this to persist the favorite status of the offers
* Kotlin serialization - Using this instead of something like Moshi or Gson, seems to work well here
* sdp/ssp - These are scalable size units meant to be friendly to differing screen sizes
* Glide - My favorite image loader, seems a bit better than others
* Navigation component - Much simpler/easier than the older way of managing the back stack
* MVVM - Feels like the cleanest architecture and the one with which I'm most familiar
* View Binding - It's more performant than data binding
* Coroutines - Using Kotlin so might as well use coroutines, also much cleaner and quicker to get going

# General Comments

* Per my email exchange with Lucas, I wasn't entirely sure what the exact width:height ratio was supposed to be for the offers so I just made it something close and kept it consistent
* I noticed the instructions say to use a Toolbar for Android. I'm not entirely sure why it's needed since the project calls for a different screen when an offer is clicked rather than relying on the toolbar for navigation. It's still there but doesn't do anything.
* The instructions say to use Roboto for the font, my understanding is that this is now simply called sans-serif
* I left the colors for the toolbar and switch that I used at their defaults since there's no specific guidance on it
* For the purposes of this project, I just opened the JSON file to check to see if anything was null and saw that two images were null values. This is the only attribute I have as nullable in the project but could obviously change that if circumstances changed.