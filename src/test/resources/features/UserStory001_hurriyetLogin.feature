  @US001
  Feature: US001 Kullanici Profilini Duzenler

    Scenario: kullanici "hurriyet" sitesine login yapar
      Given kullanici "hurriyet" sitesine gider
      Then kullanici login yapar
      And kullanici hesabina gider
      And uyelik bilgilerine tiklar
      And bilgileri degistirmek icin duzenleye tiklar
      And kullanici  bilgileri degistirir
      When bilgilerinin degistigini dogrular


