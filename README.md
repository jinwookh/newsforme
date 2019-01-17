# Newsforme
Shows 10 url of news which is sent by newsapi.com

Functions
----------
1. Shows title, and short skim of news.
<div>
<img width = "200" src = "https://user-images.githubusercontent.com/31182783/48594871-d489ce00-e995-11e8-9f38-209e1e095dd8.jpg">
</div>

2. You can select the media or country news you want at setting page.
<div>
  <img width = "200" src = "https://user-images.githubusercontent.com/31182783/51304706-3345f380-1a7c-11e9-8ff8-ff0dc10cc6f9.png">
  </div>
 
Main File Description
----------
1. NewsAsyncLoader.java: do url connection, http request, json
parsing
2. SettingDbHelper.java: store DB information
3. MainActivity.java: show news
4. SettingActivity.java: receive setting information from user,
and store at DB
