# Coletor de dados (Fingerprint) indoor

Aplicativo para auxiliar na coleta de dados para a implementação de técnicas de localização indoor baseadas  em fingerprint

**Requisitos**

Android Studio 2.3 ou superior.
Android SDK 25 instalados.

**Instalação**

Baixe o projeto do GitHub. 
- Abra o projeto com o Android Studio. 
- O Gradle irá fazer o build do projeto, instale o que o gradlew solicitar. 
- Click no botão "Sync project with gradle file" para baixar as dependências.
- Habilite a depuração USB em seu celular android 4.4 ou superior e conecte o celular ao PC na porta USB. 
- Click em "RUN" para executar o projeto no celular.

**Funcionalidades implementadas**

- Localiza redes Wi-Fi próximas
- Permite que o usuário selecione quais redes ele deseja coletar Fingerprint- 
- Coleta os dados do Wi-Fi (nome do AP, potência do sinal, RSSI) e armazena na memória interna do dispositivo no formato JSON
- Permite uma visualização rápida do arquivo Json salvo.
