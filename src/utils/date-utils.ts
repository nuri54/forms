import { formatDate } from 'date-fns'

export const formatDateToDateAndTime = (date: string) => {
  return formatDate(date, 'dd.MM.yyyy HH:mm')
}
