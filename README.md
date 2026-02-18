# CareerHabrParser

Android app that fetches and displays job vacancies from career.habr.com by parsing HTML pages.

## Features
- Vacancy feed (company, title, salary, skills, date)
- Vacancy details screen
- Pull-to-refresh
- Basic MVVM with ViewModel + LiveData
- Background loading with Kotlin Coroutines

## Tech Stack
- Kotlin, XML (Fragments)
- MVVM (ViewModel, LiveData)
- Kotlin Coroutines (viewModelScope)
- Jsoup (HTML parsing)
- RecyclerView
- Navigation Component
- Picasso (images)
- SwipeRefreshLayout

## Architecture (high level)
UI (Fragments) -> ViewModel -> Repository (Jsoup parsing) -> Models

## Screenshots
<p align="center">
  <img src="https://user-images.githubusercontent.com/55358203/146768981-7ddac5bf-a6cf-4357-96c3-119c7a62a84c.jpg" width="250" />
  <img src="https://user-images.githubusercontent.com/55358203/146768986-8bea04ea-f621-4d2f-903a-cd5aa7c65070.jpg" width="250" /> 
  <img src="https://user-images.githubusercontent.com/55358203/146768989-3d2134c8-700f-4b04-bd3c-3e6fb4d0a0a5.jpg" width="250" />
</p>

## Notes
- This app parses HTML pages, so selectors may break if the website changes its layout.

## Improvements (planned)
- Remove static context usage to avoid memory leaks
- Add proper error handling (loading/error states)
- Replace deprecated dependencies and update target SDK


  

