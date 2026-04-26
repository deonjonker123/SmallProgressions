# Small Progressions 2

**A collection of simple utility blocks and light progressive systems for everyday automation.**

(This version is a major redesign and does NOT include many features from the legacy version (armor, tools, crops, food, etc.))

---

## What This Mod Adds

### Functional Blocks

#### Automation & Generation
- **Cobblestone Generators (Tier 1-5)** - Automatically generates cobblestone at increasing speeds
- **Lava Generator** - Passively generates lava, outputs to adjacent tanks/pipes
- **Water Reservoir** - Infinite water source in a single block (configurable)
- **Lava Infused Stone** - Acts as a lava source, converts adjacent water source to obsidian
- **Simple Item Collector** - Collects items in 3x3x3 area
- **Advanced Item Collector** - Collects items in 9x9x9 area with filters and offset controls
- **Harvester** - Automatically harvests and replants crops

#### Farming & Growth
- **Growth Crystals (Tier 1-3 - Waterloggable)** - Accelerates crop growth
- **Greenhouse Glass** - Boosts crop growth when in direct sunlight

#### Processing & Storage
- **Brick Furnace** - Smelts 4x faster than vanilla furnace
- **Storage Barrels** - Copper (45), Iron (54), Gold (72), Diamond (104) slots
- **Fluid Tanks** - Copper (16), Iron (32), Gold (64), Diamond (128) buckets
- **Linen Sack** - 9-slot portable storage that keeps contents when broken

#### Special Blocks
- **McFloaty Block** - Can be placed mid-air, negates fall damage

### Materials
- **Charcoal Block**

## Configuration

All major features are fully configurable:
- Growth Crystal acceleration rates
- Greenhouse Glass boost and range
- Cobblestone Generator speeds
- Lava Generator production rate
- Water Reservoir infinite mode

---

# Solar

Lightweight, no-nonsense RF solar generation and storage.

No multiblocks. No complex machines. No inverters. Just panels, batteries, and wireless power.

---

## Features

- Five tiers of solar panels, from basic early-game generation to serious endgame power
- Five tiers of batteries with built-in item charging and draining slots
- Wireless energy transmission via the Energy Transmitter and five tiers of Energy Receivers
- Private/public wireless network toggle — keep your power to yourself or share it with others
- Jade support for all blocks

---

## Blocks

### Solar Panels

Solar panels generate RF when exposed to direct skylight during the day. Generation stops at night, during thunderstorms, and when the sky above is blocked.

| Panel | Generation |
|---|---|
| Basic Solar Panel | 128 RF/t |
| Hardened Solar Panel | 512 RF/t |
| Advanced Solar Panel | 2,048 RF/t |
| Elite Solar Panel | 8,192 RF/t |
| Ultimate Solar Panel | 32,768 RF/t |

### Batteries

Batteries store RF and can charge or drain powered items placed in their slots. Stored RF persists when the block is broken and picked up.

| Battery | Capacity | Transfer Rate |
|---|---|---|
| Basic Battery | 2,000,000 RF | 1,024 RF/t |
| Hardened Battery | 8,000,000 RF | 4,096 RF/t |
| Advanced Battery | 32,000,000 RF | 16,384 RF/t |
| Elite Battery | 64,000,000 RF | 65,536 RF/t |
| Ultimate Battery | 128,000,000 RF | 262,144 RF/t |

### Energy Transmitter

The Energy Transmitter accepts RF from any side and feeds it into the wireless network pool. The pool is shared across all dimensions and persists across server restarts. Maximum pool capacity is 2,147,483,647 RF. The transmitter also supports item charging and draining via its GUI.

By default the transmitter is **private** — only receivers placed by the same player can draw from its pool. It can be toggled to **public** via the GUI, allowing any player's receivers to draw from it.

### Energy Receivers

Energy Receivers pull RF from the wireless network pool and push it into any adjacent energy-accepting block. No pairing, no channels — if there's power in an accessible pool, receivers distribute it. Receivers always have access to their owner's private pool, plus any public pools from other players.

| Receiver | Transfer Rate |
|---|---|
| Basic Energy Receiver | 1,024 RF/t |
| Hardened Energy Receiver | 4,096 RF/t |
| Advanced Energy Receiver | 16,384 RF/t |
| Elite Energy Receiver | 65,536 RF/t |
| Ultimate Energy Receiver | 262,144 RF/t |

---

## Notes

- Solar panels only generate power with a clear line of sight to the sky
- Thunderstorms stop solar generation entirely
- Battery RF is stored in the item's data component and survives breaking and replacing
- The wireless network pool is global and cross-dimensional

---

# Wireless Redstone

Adds wireless redstone signal transmission. No fuss, no complex setup — place, set a channel, done.

---

## Blocks

### Wireless Transmitter
Broadcasts a redstone signal wirelessly to all Receivers on the same channel within range.

### Wireless Receiver
Outputs a redstone signal when a Transmitter on the same channel is powered.

### Timer
A configurable redstone clock that pulses a signal at a set interval.
- Always running by default
- Can be started/stopped from the GUI
- Outputs signal from the front face only
- Horizontal facing, placed toward the player

---

## How It Works

1. Place a **Wireless Redstone Transmitter** and right-click it to set a channel number (e.g. `5`)
2. Place a **Wireless Redstone Receiver** and set it to the same channel
3. Power the Wireless Redstone Transmitter with a redstone signal — the Wireless Redstone Receiver will output a signal

Channels are numerical. Any positive integer is valid. Multiple Transmitters and Receivers can share the same channel.

**Channels are player-specific.** On a multiplayer server, your Transmitters will only communicate with your own Receivers — other players' blocks on the same channel number will not interfere.

---

## Configuration

| Option | Default | Min | Max | Description |
|--------|---------|-----|-----|-------------|
| `transmission_range` | `128` | `16` | `512` | Radius in blocks within which Transmitters can reach Receivers |

> **Warning:** Very large range values (above 256) may impact server performance on busy servers.

---

# Simple Wireless Logistics

Transfer items wirelessly between senders and receivers. No pipes, no routing networks — just point, link, and go.

---

## Blocks

### Logistics Sender

Pulls items from the inventory on the face it is attached to and sends them wirelessly to connected Logistics Receivers.

- Transfers **16 items every 65 ticks** by default
- Connects to up to **4 receivers** within an **8-block radius** by default
- GUI with filter slots, upgrade slots, and toggle controls
- Can be placed on any face of a block

#### GUI Controls

| Control | Description |
|---|---|
| Redstone Toggle | When enabled, the sender only operates while receiving a redstone signal |
| Distribution Mode | Toggle between **Round Robin** (cycles through receivers evenly) and **Nearest First** |
| Filter Mode | Toggle between **Allow** (only send listed items) and **Block** (send everything except listed items) |
| Filter Slots | 18 ghost slots — place items to define the filter. Empty filter allows everything |
| Upgrade Slots | 4 dedicated slots, one per upgrade type |

### Logistics Receiver

Receives items from connected Logistics Senders and inserts them into the inventory on the face it is attached to. When the connected inventory is full, the sender skips this receiver and tries the next one.

- No GUI
- No internal inventory
- Can be placed on any face of a block

---

## Items

### Connection Wrench

Used to link Senders to Receivers.

- **Right-click a Sender** — stores its position in the wrench
- **Right-click a Receiver** — links it to the stored Sender. Right-clicking an already-linked Receiver disconnects it
- **Shift+Right-click in air** — clears the stored Sender position

While holding the Connection Wrench, beams are rendered between all Senders and their connected Receivers within range.

### Upgrades

Each upgrade occupies its own dedicated slot in the Sender GUI. Up to 3 of each upgrade can be placed per slot, and effects stack across all slots.

| Upgrade | Effect per unit | Max per slot | Cap |
|---|---|---|---|
| Speed Upgrade | -20 ticks per transfer | 3 | 5 ticks minimum |
| Stack Upgrade | +16 items per transfer | 3 | 64 items maximum |
| Node Upgrade | +2 max receivers | 3 | 10 receivers maximum |
| Range Upgrade | +8 block radius | 3 | 32 blocks maximum |

---

## Credits

**Inspiration:** Some features and ideas in this mod were obviously inspired by the original Tiny Progressions mod.

**Textures:** Some textures were adapted from "Unused Minecraft Textures" by Malcolm Riley ([GitHub Repository](https://github.com/malcolmriley/unused-textures)). Licensed under Creative Commons Attribution 4.0 International License. Modifications were made to some original assets.