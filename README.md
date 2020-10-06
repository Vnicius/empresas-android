# Empresas Android

# 💼 Dependências

- [**Android KTX**](https://developer.android.com/kotlin/ktx?gclid=CjwKCAjwiOv7BRBREiwAXHbv3EwiZJSsgXNRA1cZ5BtavjrAjofzIeDe6UGwGwm3Hk9XDFqp55eKZhoCrcAQAvD_BwE&gclsrc=aw.ds) - Utilizadas algumas extensões para Kotlin-Android providas pelo Google.
- [**Material**](https://material.io/develop/android) - Escolhida para utilização dos Material Components do Google que ajudaram na construção de alguns componentes do app.
- [**Retrofit**](https://square.github.io/retrofit/) - Utilizada para a execução das requisições para a API.
- [**GSON**](https://github.com/google/gson) - Utilizada para a conversão dos dados em JSON das respostas das requisições.
- [**Kotlin Coroutines**](https://github.com/Kotlin/kotlinx.coroutines) - Utilizada para a execução de tarefas assíncronas, possui fácil utilização em relação às antigas `AsyncTask`, além de ter uma ótima integração com Kotlin e o lifecycle do Android.
- [**Coil**](https://github.com/coil-kt/coil) - Utilizada para o carregamento e cache das imagens, é mais leve que o Glide e as suas extensões facilitam seu uso com as `views`.
- [**Koin**](https://github.com/InsertKoinIO/koin) - Escolhida por ser uma biblioteca de injeção de dependência em runtime de forma simples, e pessoalmente acho mais kotlin-friendly que outras bibliotecas como `Dagger2` e `Hilt`.
- [**Internet Checker**](https://github.com/Vnicius/internet-checker) - Uma biblioteca pessoal que criei há algum tempo e ajuda a checar se o usuário possui conexão com a internet no momento.

# 🕰️ Com mais tempo eu faria...

Com mais tempo eu gastaria definindo alguns testes unitários e de UI, não é algo que tenho muita experiência, então me tomaria um tempo de estudo para uma boa definição e implementação dos testes.

# 🏃‍♀️ Executando

- Android Studio - Abrindo o diretório `AppEmpresas` no Android Studio
- APK - Abrindo o arquivo `app.apk` num dispositivo Android ou executando o seguinte comando com um dispositivo Android conectado ao computador via usb.

```
    $ adb install app.apk
```
