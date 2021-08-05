(1..10).each {
  job("example-${it}") {
    steps {
      shell('echo Hello World!')
    }
  }
}

listView('example') {
  jobs {
    regex(/example-[0-9]+/)
  }
  columns{
    status()
    weather()
    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
}
