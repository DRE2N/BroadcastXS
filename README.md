![BroadcastXS](http://feuerstern.bplaced.net/ressourcen/logos/BroadcastXS.png)

[![Download](http://feuerstern.bplaced.net/ressourcen/buttons/Download.png)](http://feuerstern.bplaced.net/repo/io/github/dre2n/broadcastsxs/)

# What is BroadcastXS?
BroadcastXS is a broadcast plugin.

It provides basic configurability, but primarily, BXS aims to be as simple as possible to offer best performance. The broadcasts are sent asynchronously; therefore, the influence to the TPS is insignificant.

# Configuration instructions
```
configVersion: 2
interval: 5.0
messages:
- '&6This is a normal chat message.'
- "Instead of using the YAML formatting, you can also use <br> as a line break."
- "centered:As you see,<br>does not break centered texts."
- centered:&9This is a centered chat message.
- actionBar:&3This is an action bar message.
- title:&7This is a title message.
- title:&7This is a title message...subtitle:&b...and this is a subtitle.
prefix: '&4&l[MyPrefix] &r'
fadeIn: 1.0
show: 3.0
fadeOut: 1.0
```

# Compatibilty
### Server
_BroadcastXS_ works with Bukkit 1.4.2 and higher. Some features like titles and action bar messages require recent versions.

### Java
7 and higher

### Known incompatibilities
None
