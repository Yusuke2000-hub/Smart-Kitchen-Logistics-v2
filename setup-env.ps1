$env:JAVA_HOME = "$env:USERPROFILE\.jdks\ms-21.0.10"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
Write-Host "JAVA_HOME を設定しました: $env:JAVA_HOME" -ForegroundColor Green