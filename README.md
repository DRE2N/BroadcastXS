![BroadcastXS](http://feuerstern.bplaced.net/ressourcen/logos/BroadcastXS.png)

[![Download](http://feuerstern.bplaced.net/ressourcen/buttons/Download.png)](http://erethon.de/repo/de/erethon/broadcastxs)

# What is BroadcastXS?
BroadcastXS is a broadcast plugin.

It has many useful features such as centered messages and action bars, but primarily, BXS aims to be as simple as possible to offer best performance.

# Configuration instructions
## config.yml
```
# Do not modify this.
configVersion: 5
# The language of plugin messages.
language: english
# The broadcast interval in seconds.
interval: 5.0
messages:
- "&6This is a normal chat message."
- "Instead of using the YAML formatting, you can also use <br> as a line break."
- "centered:As you see,<br>does not break centered texts."
- "centered:&9This is a centered chat message."
- "actionBar:&3This is an action bar message."
- "title:&7This is a title message."
- "title:&7This is a title message...subtitle:&b...and this is a subtitle."
# If you do not want a prefix / header / footer, leave this empty.
prefix: "&4&l[MyPrefix] &r"
header: "centered:&6==== &bHEADER &6===="
footer: "centered:&6==== &bFOOTERs&6====<br>also support formatting tags!"
# Title settings
fadeIn: 1.0
show: 3.0
fadeOut: 1.0
# If excluded players should be saved
saveToggle: false
# A list of the UUIDs of all excluded players
excludedPlayers: []
```

## Commands and permissions
### /bxs reload | bxs.reload
Reloads the configuration file and restarts the broadcast task.

### /bxs bc [index number|text] | bxs.broadcast
Broadcasts the text or a message from config.

### /bxs toggle | bxs.toggle
Toggled broadcast messages.

# Compatibilty
### Server
_BroadcastXS_ works with Paper 1.18.2 and higher. 

### Java
17 and higher

### Known incompatibilities
None
