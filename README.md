# Ktor issue #384 reproduction

Sample project that demonstrates that Ktor doesn't use UTF-8 as default encoding:

https://github.com/ktorio/ktor/issues/384

## Steps to reproduce

Start server:
```
./gradlew run
```

Send POST request:

```bash
curl -v -X POST \
    -H 'Content-Type: application/json;' \
    -d '@src/main/resources/body.json' \
    'http://127.0.0.1:8081/'
```

You will get 500 error with message:
```
Request body is incorrect:
{"messages":["HELLO", "OLÃ", "ÐÐ ÐÐÐÐ¢", "â¦"]}

expected:
{"messages":["HELLO", "OLÀ", "ПРИВЕТ", "…"]}
```

But if you explicitly add charset:

```bash
curl -v -X POST \
    -H 'Content-Type: application/json;charset=utf-8' \
    -d '@src/main/resources/body.json' \
    'http://127.0.0.1:8081/'
```

You will see:
```
Body is valid
```