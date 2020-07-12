# Cards Demo

## Building for local server

```bash
$ ./gradlew assembleLocal
```

The client will connect to `http://10.0.2.2:8000/` for use within an emulator.

## Code style check

```bash
$ ./gradlew spotlessCheck
```

## Unit tests

1. [BalanceIndicatorPresenter](cards/src/test/kotlin/com/ekezet/cardsdemo/cards/parts/cardsPage/parts/balanceIndicator/BalanceIndicatorPresenterTest.kt)
