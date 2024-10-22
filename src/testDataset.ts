export const testData = {
  steps: [
    {
      inputs: [
        {
          name: 'Vorname',
          value: null,
          type: 'text'
        },
        {
          name: 'Name',
          value: null,
          type: 'text'
        },
        {
          name: 'Studiengang',
          value: null,
          type: 'text'
        },
        {
          name: 'Semester',
          value: null,
          type: 'number'
        },
        {
          name: 'Matrikelnummer',
          value: null,
          type: 'number'
        },
        {
          name: 'Straße',
          value: null,
          type: 'text'
        },
        {
          name: 'Postleitzahl',
          value: null,
          type: 'number'
        },
        {
          name: 'Wohnort',
          value: null,
          type: 'text'
        },
        {
          name: 'Telefon',
          value: null,
          type: 'phoneNumber'
        },
        {
          name: 'E-Mail',
          value: null,
          type: 'email'
        },
        {
          name: 'Titel der Thesis',
          value: null,
          type: 'textbox'
        },
        {
          name: 'Zielperson',
          value: null,
          type: 'account'
        }
      ]
    },

    {
      inputs: [
        {
          name: 'Betreuer',
          value: null,
          type: 'text'
        },
        {
          name: 'Unternehmen',
          value: null,
          type: 'text'
        },
        {
          name: 'Es wird bestätigt, dass die Betreuung des unter 1. angeführten Themas übernommen wird und die nachstehenden Voraussetzungen für die Ausgabe der Abschlussarbeit erfüllt sind:',
          options: [
            {
              name: 'Einhaltung der Ausgabezeit gem. § 26 Abs. 1 SPO AT (Bachelor)sowie § 21 Abs. 1 SPO AT (Master)',
              value: false
            },
            {
              name: 'Nachweis der erfolgreichen Teilnahme am praktischen Studiensemester (Ausnahme Master, sowie Studiengänge BKB und BS).',
              value: false
            }
          ],
          type: 'checkbox'
        },
        {
          name: 'Ausgabedatum',
          value: null,
          type: 'date'
        },
        {
          name: 'Zweitprüfer',
          value: null,
          type: 'text'
        },
        {
          name: 'Zielperson',
          value: null,
          type: 'account'
        }
      ]
    },

    {
      inputs: [
        {
          name: 'Betreuer',
          value: null,
          type: 'text'
        },
        {
          name: 'Zweitprüfer',
          value: null,
          type: 'text'
        },
        {
          name: 'Abgabedatum',
          value: null,
          type: 'date'
        }
      ]
    }
  ],

  beteiligte: ['account', 'account', 'account']
}
