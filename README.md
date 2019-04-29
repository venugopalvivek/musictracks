# Music Tracks
Extremely Simplified 8tracks.com (API only)

## Features
* list tags starts with prefix
* create playlist with tags
* delete playlist
* get playlist
* explore playlists given a set of tags (Logical OR on tags)

## Assumptions
* Not supporting uploading of songs. For now, song ids can be any string value
* All tags are considered as equal (no categorization)
* The like and play counts are assumed to be updated independently.
  No API support provided

## Components required
* MongoDB - Chose this as it has built capability to 
    * perform regex search on texts (such as starts with)
    * perform search on list of values in a document
    * can avoid joins

## How to Run
* Clone this repo
* cd `.../musictracks`
* run `gradle clean appRun`
* If need to change the port of the jetty server change `build.gradle` file in `.../musictracks`
    * `httpPort = 8080`
* the app will be available at `http://localhost:8080/musictracks/api/...`

## Improvements
* Adding unit tests
* Checking for performance concerns on large datasets
* Investigate need to add transaction support
 
