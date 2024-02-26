import feedparser
import pprint

url = "https://hnrss.org/newest"

f = feedparser.parse(url)

for article in f['entries']:
   print('title is ' + article['title'] + '\n')
   print('link is ' + article['link'] + '\n')
   print('summary is ' + article['summary'] + '\n')
   print('\n')
   exit()