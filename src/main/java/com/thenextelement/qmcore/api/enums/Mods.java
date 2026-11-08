package com.thenextelement.qmcore.api.enums;

import java.util.Locale;

import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.gtnhlib.util.data.IMod;

import cpw.mods.fml.common.Loader;

/**
 * Every mod in the Quaesita Magum pack, for {@code isModLoaded()} checks and resource paths. Modelled on
 * GT5-Unofficial's
 * {@code gregtech.api.enums.Mods}.
 * <p>
 * IDs were read from the {@code @Mod} annotations of the jars in the MQ 2.7.1 instance (mcmod.info IDs are not always
 * the real ones). Tinkers' Construct and its addons, and Draconic Evolution, are left out on purpose.
 * <p>
 * Use {@link ModIDs} where a compile-time constant is needed, e.g. {@code @Optional.Method(modid = ModIDs.THAUMCRAFT)}.
 */
public enum Mods implements IMod {

    AdjaChest(ModIDs.ADJA_CHEST),
    AdvancedBotany(ModIDs.ADVANCED_BOTANY),
    AdvancedThaumaturgy(ModIDs.ADVANCED_THAUMATURGY),
    AdventureBackpack(ModIDs.ADVENTURE_BACKPACK),
    AdventurersAmulets(ModIDs.ADVENTURERS_AMULETS),
    AetherLegacy(ModIDs.AETHER_LEGACY),
    AgriCraft(ModIDs.AGRI_CRAFT),
    AkashicTome(ModIDs.AKASHIC_TOME),
    AlchemyGrate(ModIDs.ALCHEMY_GRATE),
    AmazingTrophies(ModIDs.AMAZING_TROPHIES),
    Angelica(ModIDs.ANGELICA),
    AnimationAPI(ModIDs.ANIMATION_API),
    AnotherOneBitesTheDust(ModIDs.ANOTHER_ONE_BITES_THE_DUST),
    Antiquities(ModIDs.ANTIQUITIES),
    AppleCore(ModIDs.APPLE_CORE),
    AquaAcrobatics(ModIDs.AQUA_ACROBATICS),
    AquaCreeper(ModIDs.AQUA_CREEPER),
    ArchaicFix(ModIDs.ARCHAIC_FIX),
    ArchitectureCraft(ModIDs.ARCHITECTURE_CRAFT),
    Archmagus(ModIDs.ARCHMAGUS),
    ArsMagica2(ModIDs.ARS_MAGICA2),
    AsieLib(ModIDs.ASIE_LIB),
    AspectRecipeIndex(ModIDs.ASPECT_RECIPE_INDEX),
    Automagy(ModIDs.AUTOMAGY),
    Backhand(ModIDs.BACKHAND),
    Backpack(ModIDs.BACKPACK),
    Baubles(ModIDs.BAUBLES),
    BaublesExpanded(ModIDs.BAUBLES_EXPANDED),
    BDLib(ModIDs.BD_LIB),
    BeeBetterAtBees(ModIDs.BEE_BETTER_AT_BEES),
    BetterAchievements(ModIDs.BETTER_ACHIEVEMENTS),
    BetterBuildersWands(ModIDs.BETTER_BUILDERS_WANDS),
    BetterCrashes(ModIDs.BETTER_CRASHES),
    BetterFoliage(ModIDs.BETTER_FOLIAGE),
    BetterLoadingScreen(ModIDs.BETTER_LOADING_SCREEN),
    BetterQuesting(ModIDs.BETTER_QUESTING),
    BetterQuestingStandardExpansion(ModIDs.BETTER_QUESTING_STANDARD_EXPANSION),
    BiblioCraft(ModIDs.BIBLIO_CRAFT),
    BiblioWoodsBoPEdition(ModIDs.BIBLIO_WOODS_BO_P_EDITION),
    BiblioWoodsForestryEdition(ModIDs.BIBLIO_WOODS_FORESTRY_EDITION),
    BiblioWoodsNaturaEdition(ModIDs.BIBLIO_WOODS_NATURA_EDITION),
    BinnieCore(ModIDs.BINNIE_CORE),
    BiomesOPlenty(ModIDs.BIOMES_O_PLENTY),
    BloodArsenal(ModIDs.BLOOD_ARSENAL),
    BloodMagic(ModIDs.BLOOD_MAGIC),
    Blur(ModIDs.BLUR),
    Bookshelf(ModIDs.BOOKSHELF),
    Botania(ModIDs.BOTANIA),
    Botany(ModIDs.BOTANY),
    BrewingAPI(ModIDs.BREWING_API),
    BugTorch(ModIDs.BUG_TORCH),
    CampfireBackport(ModIDs.CAMPFIRE_BACKPORT),
    CarbonConfigLibrary(ModIDs.CARBON_CONFIG_LIBRARY),
    CarpentersBlocks(ModIDs.CARPENTERS_BLOCKS),
    ChestTransporter(ModIDs.CHEST_TRANSPORTER),
    Chisel(ModIDs.CHISEL),
    ChunkAPI(ModIDs.CHUNK_API),
    ClaySoldiersMod(ModIDs.CLAY_SOLDIERS_MOD),
    CodeChickenCore(ModIDs.CODE_CHICKEN_CORE),
    CoFHCore(ModIDs.CO_FH_CORE),
    Companions(ModIDs.COMPANIONS),
    Controlling(ModIDs.CONTROLLING),
    CookingForBlockheads(ModIDs.COOKING_FOR_BLOCKHEADS),
    CoreTweaks(ModIDs.CORE_TWEAKS),
    CosmeticArmorReworked(ModIDs.COSMETIC_ARMOR_REWORKED),
    CraftTweaker(ModIDs.CRAFT_TWEAKER),
    Minecraft(ModIDs.MINECRAFT),
    CreativeCore(ModIDs.CREATIVE_CORE),
    CustomMainMenu(ModIDs.CUSTOM_MAIN_MENU),
    D3Core(ModIDs.D3_CORE),
    DarkMenagerie(ModIDs.DARK_MENAGERIE),
    DeathlyHallows(ModIDs.DEATHLY_HALLOWS),
    DefaultConfigs(ModIDs.DEFAULT_CONFIGS),
    DefaultServerList(ModIDs.DEFAULT_SERVER_LIST),
    DefaultWorldGenerator(ModIDs.DEFAULT_WORLD_GENERATOR),
    DenseOres(ModIDs.DENSE_ORES),
    DummyCore(ModIDs.DUMMY_CORE),
    DynamicSurroundings(ModIDs.DYNAMIC_SURROUNDINGS),
    Enchiridion(ModIDs.ENCHIRIDION),
    Enchiridion2(ModIDs.ENCHIRIDION2),
    EnderCore(ModIDs.ENDER_CORE),
    EnderStorage(ModIDs.ENDER_STORAGE),
    EnderZoo(ModIDs.ENDER_ZOO),
    EndlessIDs(ModIDs.ENDLESS_I_DS),
    EnhancedLootBags(ModIDs.ENHANCED_LOOT_BAGS),
    EtFuturumRequiem(ModIDs.ET_FUTURUM_REQUIEM),
    EuphoriaPatcher(ModIDs.EUPHORIA_PATCHER),
    ExtraBees(ModIDs.EXTRA_BEES),
    ExtraTrees(ModIDs.EXTRA_TREES),
    ExtremeSoundMufflerLegacy(ModIDs.EXTREME_SOUND_MUFFLER_LEGACY),
    FairyLights(ModIDs.FAIRY_LIGHTS),
    FalsePatternLib(ModIDs.FALSE_PATTERN_LIB),
    Farseek(ModIDs.FARSEEK),
    Fether(ModIDs.FETHER),
    FindIt(ModIDs.FIND_IT),
    ForbiddenMagic(ModIDs.FORBIDDEN_MAGIC),
    Forestry(ModIDs.FORESTRY),
    Forgelin(ModIDs.FORGELIN),
    ForgeMicroblocks(ModIDs.FORGE_MICROBLOCKS),
    ForgeMultipart(ModIDs.FORGE_MULTIPART),
    ForgeRelocation(ModIDs.FORGE_RELOCATION),
    ForgottenRelics(ModIDs.FORGOTTEN_RELICS),
    Gadomancy(ModIDs.GADOMANCY),
    GardenContainers(ModIDs.GARDEN_CONTAINERS),
    GardenCore(ModIDs.GARDEN_CORE),
    GardenStuff(ModIDs.GARDEN_STUFF),
    GardenTrees(ModIDs.GARDEN_TREES),
    Genetics(ModIDs.GENETICS),
    GregoryTweaksForCrafting(ModIDs.GREGORY_TWEAKS_FOR_CRAFTING),
    Growthcraft(ModIDs.GROWTHCRAFT),
    GrowthcraftApples(ModIDs.GROWTHCRAFT_APPLES),
    GrowthcraftBamboo(ModIDs.GROWTHCRAFT_BAMBOO),
    GrowthcraftBees(ModIDs.GROWTHCRAFT_BEES),
    GrowthcraftCellar(ModIDs.GROWTHCRAFT_CELLAR),
    GrowthcraftFishtrap(ModIDs.GROWTHCRAFT_FISHTRAP),
    GrowthcraftGrapes(ModIDs.GROWTHCRAFT_GRAPES),
    GrowthcraftHops(ModIDs.GROWTHCRAFT_HOPS),
    GrowthcraftMilk(ModIDs.GROWTHCRAFT_MILK),
    GrowthcraftRice(ModIDs.GROWTHCRAFT_RICE),
    GTNHExtLib(ModIDs.GTNH_EXT_LIB),
    GTNHLib(ModIDs.GTNH_LIB),
    GuideAPI(ModIDs.GUIDE_API),
    Hammerz(ModIDs.HAMMERZ),
    HardcoreEnderExpansion(ModIDs.HARDCORE_ENDER_EXPANSION),
    HelpFixer(ModIDs.HELP_FIXER),
    HodgePodge(ModIDs.HODGE_PODGE),
    HoloInventory(ModIDs.HOLO_INVENTORY),
    HookLibUltimate(ModIDs.HOOK_LIB_ULTIMATE),
    HopperDucts(ModIDs.HOPPER_DUCTS),
    InGameInfoXML(ModIDs.IN_GAME_INFO_XML),
    InventoryBogoSorter(ModIDs.INVENTORY_BOGO_SORTER),
    IronChests(ModIDs.IRON_CHESTS),
    IronChestsMinecarts(ModIDs.IRON_CHESTS_MINECARTS),
    IvToolkit(ModIDs.IV_TOOLKIT),
    JABBA(ModIDs.JABBA),
    JourneyMap(ModIDs.JOURNEY_MAP),
    LittleTiles(ModIDs.LITTLE_TILES),
    LootGames(ModIDs.LOOT_GAMES),
    LostBooks(ModIDs.LOST_BOOKS),
    LunatriusCore(ModIDs.LUNATRIUS_CORE),
    LWJGL3ify(ModIDs.LWJGL3IFY),
    MagiaNaturalis(ModIDs.MAGIA_NATURALIS),
    MagicalCrops(ModIDs.MAGICAL_CROPS),
    MagicalDecorations(ModIDs.MAGICAL_DECORATIONS),
    MagicBees(ModIDs.MAGIC_BEES),
    MalisisCore(ModIDs.MALISIS_CORE),
    MalisisDoors(ModIDs.MALISIS_DOORS),
    MCFrames(ModIDs.MC_FRAMES),
    MineAndBladeBattleGear2(ModIDs.MINE_AND_BLADE_BATTLE_GEAR2),
    MmmMmmMmmMmm(ModIDs.MMM_MMM_MMM_MMM),
    MobsInfo(ModIDs.MOBS_INFO),
    ModernMarkings(ModIDs.MODERN_MARKINGS),
    ModTweaker(ModIDs.MOD_TWEAKER),
    ModularUI2(ModIDs.MODULAR_UI2),
    MoreChisels(ModIDs.MORE_CHISELS),
    MouseTweaks(ModIDs.MOUSE_TWEAKS),
    MrTJPCore(ModIDs.MR_TJP_CORE),
    MyCTMLib(ModIDs.MY_CTM_LIB),
    Natura(ModIDs.NATURA),
    NaturesCompass(ModIDs.NATURES_COMPASS),
    Navigator(ModIDs.NAVIGATOR),
    Neat(ModIDs.NEAT),
    Necromancy(ModIDs.NECROMANCY),
    NEIAddons(ModIDs.NEI_ADDONS),
    NEIIntegration(ModIDs.NEI_INTEGRATION),
    NemexLib(ModIDs.NEMEX_LIB),
    Netherlicious(ModIDs.NETHERLICIOUS),
    NetherPortalFix(ModIDs.NETHER_PORTAL_FIX),
    NodalMechanics(ModIDs.NODAL_MECHANICS),
    NoHotbarNeeded(ModIDs.NO_HOTBAR_NEEDED),
    NotEnoughItems(ModIDs.NOT_ENOUGH_ITEMS),
    NovaCraft(ModIDs.NOVA_CRAFT),
    Nutrition(ModIDs.NUTRITION),
    OAuth(ModIDs.O_AUTH),
    OpenBlocks(ModIDs.OPEN_BLOCKS),
    OpenModsCore(ModIDs.OPEN_MODS_CORE),
    OpenModsLib(ModIDs.OPEN_MODS_LIB),
    OreExcavation(ModIDs.ORE_EXCAVATION),
    OreTweaker(ModIDs.ORE_TWEAKER),
    Origin(ModIDs.ORIGIN),
    OverloadedArmorBar(ModIDs.OVERLOADED_ARMOR_BAR),
    PamsHarvestCraft(ModIDs.PAMS_HARVEST_CRAFT),
    PlaceableItemsMod(ModIDs.PLACEABLE_ITEMS_MOD),
    Platforms(ModIDs.PLATFORMS),
    Postea(ModIDs.POSTEA),
    ProjectRedCompatibility(ModIDs.PROJECT_RED_COMPATIBILITY),
    ProjectRedCore(ModIDs.PROJECT_RED_CORE),
    ProjectRedExpansion(ModIDs.PROJECT_RED_EXPANSION),
    ProjectRedExploration(ModIDs.PROJECT_RED_EXPLORATION),
    ProjectRedFabrication(ModIDs.PROJECT_RED_FABRICATION),
    ProjectRedIllumination(ModIDs.PROJECT_RED_ILLUMINATION),
    ProjectRedIntegration(ModIDs.PROJECT_RED_INTEGRATION),
    ProjectRedTransmission(ModIDs.PROJECT_RED_TRANSMISSION),
    ProjectRedTransportation(ModIDs.PROJECT_RED_TRANSPORTATION),
    QuestBook(ModIDs.QUEST_BOOK),
    RandomBoubles(ModIDs.RANDOM_BOUBLES),
    RandomThings(ModIDs.RANDOM_THINGS),
    RealisticWorldGen2(ModIDs.REALISTIC_WORLD_GEN2),
    Reliquary(ModIDs.RELIQUARY),
    RelocationFMP(ModIDs.RELOCATION_FMP),
    RoguelikeDungeons(ModIDs.ROGUELIKE_DUNGEONS),
    Ruins(ModIDs.RUINS),
    RWG(ModIDs.RWG),
    SalisArcana(ModIDs.SALIS_ARCANA),
    SanAndreasPsManagerPack(ModIDs.SAN_ANDREAS_PS_MANAGER_PACK),
    Sanguimancy(ModIDs.SANGUIMANCY),
    SanguimancyPatch(ModIDs.SANGUIMANCY_PATCH),
    SanguineUtilities(ModIDs.SANGUINE_UTILITIES),
    Schematica(ModIDs.SCHEMATICA),
    ServerUtilities(ModIDs.SERVER_UTILITIES),
    ShareWhereIAm(ModIDs.SHARE_WHERE_I_AM),
    ShetiPhianCore(ModIDs.SHETI_PHIAN_CORE),
    SimpleSkinBackport(ModIDs.SIMPLE_SKIN_BACKPORT),
    SleepingBag(ModIDs.SLEEPING_BAG),
    Snad(ModIDs.SNAD),
    SoundFilters(ModIDs.SOUND_FILTERS),
    SpecialMobs(ModIDs.SPECIAL_MOBS),
    SpiceOfLife(ModIDs.SPICE_OF_LIFE),
    StorageDrawers(ModIDs.STORAGE_DRAWERS),
    StorageDrawersBiomesOPlentyPack(ModIDs.STORAGE_DRAWERS_BIOMES_O_PLENTY_PACK),
    StorageDrawersErebusPack(ModIDs.STORAGE_DRAWERS_EREBUS_PACK),
    StorageDrawersForestryPack(ModIDs.STORAGE_DRAWERS_FORESTRY_PACK),
    StorageDrawersMiscPack(ModIDs.STORAGE_DRAWERS_MISC_PACK),
    StorageDrawersNaturaPack(ModIDs.STORAGE_DRAWERS_NATURA_PACK),
    Streams(ModIDs.STREAMS),
    StructureCompat(ModIDs.STRUCTURE_COMPAT),
    StructureLib(ModIDs.STRUCTURE_LIB),
    SuperCraftingFrame(ModIDs.SUPER_CRAFTING_FRAME),
    TailsLegacy(ModIDs.TAILS_LEGACY),
    TaintedMagic(ModIDs.TAINTED_MAGIC),
    Talismans2(ModIDs.TALISMANS2),
    TC4Tweaks(ModIDs.TC4_TWEAKS),
    TCNodeTracker(ModIDs.TC_NODE_TRACKER),
    ThaumaturgicalKnowledge(ModIDs.THAUMATURGICAL_KNOWLEDGE),
    Thaumcraft(ModIDs.THAUMCRAFT),
    ThaumcraftMobAspects(ModIDs.THAUMCRAFT_MOB_ASPECTS),
    ThaumcraftResearchTweaks(ModIDs.THAUMCRAFT_RESEARCH_TWEAKS),
    ThaumicAlchemy(ModIDs.THAUMIC_ALCHEMY),
    ThaumicBases(ModIDs.THAUMIC_BASES),
    ThaumicBoots(ModIDs.THAUMIC_BOOTS),
    ThaumicDarkness(ModIDs.THAUMIC_DARKNESS),
    ThaumicDyes(ModIDs.THAUMIC_DYES),
    ThaumicExploration(ModIDs.THAUMIC_EXPLORATION),
    ThaumicHorizons(ModIDs.THAUMIC_HORIZONS),
    ThaumicInsurgence(ModIDs.THAUMIC_INSURGENCE),
    ThaumicMachina(ModIDs.THAUMIC_MACHINA),
    ThaumicRenaissance(ModIDs.THAUMIC_RENAISSANCE),
    ThaumicTinkerer(ModIDs.THAUMIC_TINKERER),
    TooMuchLoot(ModIDs.TOO_MUCH_LOOT),
    Totemic(ModIDs.TOTEMIC),
    Translocator(ModIDs.TRANSLOCATOR),
    TravellersGear(ModIDs.TRAVELLERS_GEAR),
    TravellersGearNeo(ModIDs.TRAVELLERS_GEAR_NEO),
    TwilightForest(ModIDs.TWILIGHT_FOREST),
    TXLoader(ModIDs.TX_LOADER),
    UniLib(ModIDs.UNI_LIB),
    UniMixins(ModIDs.UNI_MIXINS),
    VillageNames(ModIDs.VILLAGE_NAMES),
    VillagersNose(ModIDs.VILLAGERS_NOSE),
    Waila(ModIDs.WAILA),
    WailaHarvestability(ModIDs.WAILA_HARVESTABILITY),
    WailaPlugins(ModIDs.WAILA_PLUGINS),
    WanionLib(ModIDs.WANION_LIB),
    WarpTheory(ModIDs.WARP_THEORY),
    WaystonesX(ModIDs.WAYSTONES_X),
    WhatAreWeLookingAt(ModIDs.WHAT_ARE_WE_LOOKING_AT),
    Witchery(ModIDs.WITCHERY),
    WitcheryExtras(ModIDs.WITCHERY_EXTRAS),
    WitchingGadgets(ModIDs.WITCHING_GADGETS),
    WolfArmorAndStorage(ModIDs.WOLF_ARMOR_AND_STORAGE),
    YAMCore(ModIDs.YAM_CORE),
    ZTones(ModIDs.Z_TONES),
    Zume(ModIDs.ZUME);

    public final String ID;
    public final String resourceDomain;
    private boolean checked, modLoaded;

    Mods(String ID) {
        this.ID = ID;
        this.resourceDomain = ID.toLowerCase(Locale.ENGLISH);
    }

    /** Cached after the first call; only valid once FML has finished constructing mods. */
    @Override
    public final boolean isModLoaded() {
        if (!this.checked) {
            // FML registers vanilla as "Minecraft", so "minecraft" would read as missing
            this.modLoaded = "minecraft".equals(ID) || Loader.isModLoaded(ID);
            this.checked = true;
        }
        return this.modLoaded;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getResourceLocation() {
        return resourceDomain;
    }

    public ResourceLocation getResourceLocation(String path) {
        return new ResourceLocation(this.resourceDomain, path);
    }

    public ResourceLocation getResourceLocation(String... path) {
        return new ResourceLocation(this.resourceDomain, String.join("/", path));
    }

    public String getResourcePath(String... path) {
        return this.getResourceLocation(path)
            .toString();
    }

    /** Compile-time mod ID constants, usable in annotations. */
    public static final class ModIDs {

        private ModIDs() {}

        public static final String ADJA_CHEST = "adjachest"; // adjachest-0.9.9.9beta10.jar
        public static final String ADVANCED_BOTANY = "AdvancedBotany"; // Advanced Botany 1.3.1.7.jar
        public static final String ADVANCED_THAUMATURGY = "AdvancedThaumaturgy"; // AdvancedThaumaturgy-2.0-1.7.10-30.jar
        public static final String ADVENTURERS_AMULETS = "adventurersamulets"; // AdventurersAmulets-1.7.10-1.5.0c.jar
        public static final String ADVENTURE_BACKPACK = "adventurebackpack"; // adventurebackpack-1.4.28-GTNH.jar
        public static final String AETHER_LEGACY = "aether_legacy"; // aether-departure-1.1.2-JB.jar
        public static final String AGRI_CRAFT = "AgriCraft"; // AgriCraft-1.5.2-unofficial.jar
        public static final String AKASHIC_TOME = "akashictome"; // akashictome-1.2.7.jar
        public static final String ALCHEMY_GRATE = "alchgrate"; // alchgrate-1.3.2-GTNH.jar
        public static final String AMAZING_TROPHIES = "amazingtrophies"; // amazingtrophies-1.4.10.jar
        public static final String ANGELICA = "angelica"; // angelica-2.2.43.jar
        public static final String ANIMATION_API = "AnimationAPI"; // AnimationAPI-1.7.10-1.2.4.jar
        public static final String ANOTHER_ONE_BITES_THE_DUST = "aobd"; // AOBD-2.9.2.jar
        public static final String ANTIQUITIES = "antiquities"; // antiquities-0.2.52.jar
        public static final String APPLE_CORE = "AppleCore"; // AppleCore-3.3.13.jar
        public static final String AQUA_ACROBATICS = "aquaacrobatics"; // aquaacrobatics-0.1.0.jar
        public static final String AQUA_CREEPER = "aquacreeper"; // AquaCreepers-1.0.1.jar
        public static final String ARCHAIC_FIX = "archaicfix"; // archaicfix-0.8.2.jar
        public static final String ARCHITECTURE_CRAFT = "ArchitectureCraft"; // ArchitectureCraft-1.12.19.jar
        public static final String ARCHMAGUS = "archmagus"; // Archmagus-1.7.10-0.7.0.jar
        public static final String ARS_MAGICA2 = "arsmagica2"; // AM2.5-LTS-1.6.10-LE.jar
        public static final String ASIE_LIB = "asielib"; // AsieLib-0.7.2.jar
        public static final String ASPECT_RECIPE_INDEX = "aspectrecipeindex"; // aspectrecipeindex-1.1.6.jar
        public static final String AUTOMAGY = "Automagy"; // Automagy-0.29.8-GTNH.jar
        public static final String BACKHAND = "backhand"; // backhand-1.8.18.jar
        public static final String BACKPACK = "Backpack"; // backpack-2.6.18-GTNH.jar
        public static final String BAUBLES = "Baubles"; // BaublesExpanded-2.2.27-GTNH.jar
        public static final String BAUBLES_EXPANDED = "Baubles|Expanded"; // BaublesExpanded-2.2.27-GTNH.jar
        public static final String BD_LIB = "bdlib"; // bdlib-1.11.1-GTNH.jar
        public static final String BEE_BETTER_AT_BEES = "beebetteratbees"; // beebetteratbees-0.4.8-GTNH.jar
        public static final String BETTER_ACHIEVEMENTS = "BetterAchievements"; // BetterAchievements-0.3.3.jar
        public static final String BETTER_BUILDERS_WANDS = "betterbuilderswands"; // BetterBuildersWands-0.13.12-GTNH.jar
        public static final String BETTER_CRASHES = "bettercrashes"; // bettercrashes-1.4.7-GTNH.jar
        public static final String BETTER_FOLIAGE = "BetterFoliage"; // BetterFoliage-LegacyEdition-1.2.4.jar
        public static final String BETTER_LOADING_SCREEN = "betterloadingscreen"; // betterloadingscreen-1.7.19-GTNH.jar
        public static final String BETTER_QUESTING = "betterquesting"; // BetterQuesting-3.8.92-GTNH.jar
        public static final String BETTER_QUESTING_STANDARD_EXPANSION = "bq_standard"; // BetterQuesting-3.8.92-GTNH.jar
        public static final String BIBLIO_CRAFT = "BiblioCraft"; // BiblioCraft[v1.11.7][MC1.7.10].jar
        public static final String BIBLIO_WOODS_BO_P_EDITION = "BiblioWoodsBoP"; // BiblioWoods[BiomesOPlenty][v1.9].jar
        public static final String BIBLIO_WOODS_FORESTRY_EDITION = "BiblioWoodsForestry"; // BiblioWoods[Forestry][v1.7].jar
        public static final String BIBLIO_WOODS_NATURA_EDITION = "BiblioWoodsNatura"; // BiblioWoods[Natura][v1.5].jar
        public static final String BINNIE_CORE = "BinnieCore"; // binnie-mods-2.6.51.jar
        public static final String BIOMES_O_PLENTY = "BiomesOPlenty"; // BiomesOPlenty-1.7.10-2.1.0.2308-universal.jar
        public static final String BLOOD_ARSENAL = "BloodArsenal"; // BloodArsenal-1.5.12.jar
        public static final String BLOOD_MAGIC = "AWWayofTime"; // BloodMagic-1.9.16.jar
        public static final String BLUR = "blur"; // Blur-MC1.7.10-1.0.1-2.jar
        public static final String BOOKSHELF = "bookshelf"; // Bookshelf-1.7.10-1.0.4.187.jar
        public static final String BOTANIA = "Botania"; // Botania-1.13.34-GTNH-git.9+1cba962c88-dirty.jar
        public static final String BOTANY = "Botany"; // binnie-mods-2.6.51.jar
        public static final String BREWING_API = "brewingapi"; // Brewing-API-1.7.2-1.2.0.jar
        public static final String BUG_TORCH = "bugtorch"; // bugtorch-1.3.1.jar
        public static final String CAMPFIRE_BACKPORT = "campfirebackport"; // campfirebackport-1.7.10-1.11.3.jar
        public static final String CARBON_CONFIG_LIBRARY = "carbonconfig"; // CarbonConfig-1.7.10-2.0.2.1.jar
        public static final String CARPENTERS_BLOCKS = "CarpentersBlocks"; // CarpentersBlocks-3.7.3-GTNH.jar
        public static final String CHEST_TRANSPORTER = "ChestTransporter"; // ChestTransporter-1.7.10-2.0.6.jar
        public static final String CHISEL = "chisel"; // chisel-2.17.34-GTNH.jar
        public static final String CHUNK_API = "chunkapi"; // chunkapi-mc1.7.10-0.8.4.jar
        public static final String CLAY_SOLDIERS_MOD = "claysoldiers"; // ClaySoldiersMod-1.7.10-2.0.0-beta.2.jar
        public static final String CODE_CHICKEN_CORE = "CodeChickenCore"; // CodeChickenCore-1.4.22.jar
        public static final String COMPANIONS = "companions"; // companions-1.2.jar
        public static final String CONTROLLING = "controlling"; // controlling-2.1.10.jar
        public static final String COOKING_FOR_BLOCKHEADS = "cookingforblockheads"; // CookingForBlockheads-1.4.14-GTNH.jar
        public static final String CORE_TWEAKS = "coretweaks"; // coretweaks-0.3.4.7-GTNH.jar
        public static final String COSMETIC_ARMOR_REWORKED = "cosmeticarmorreworked"; // cosmeticarmorreworked-1.0.7-GTNH.jar
        public static final String CO_FH_CORE = "CoFHCore"; // CoFHCore-[1.7.10]3.1.4-329.jar
        public static final String CRAFT_TWEAKER = "MineTweaker3"; // CraftTweaker-1.7.10-3.1.0-legacy.jar
        public static final String MINECRAFT = "minecraft"; // vanilla
        public static final String CREATIVE_CORE = "creativecore"; // littletiles-1.6.63.jar
        public static final String CUSTOM_MAIN_MENU = "custommainmenu"; // custommainmenu-1.14.1.jar
        public static final String D3_CORE = "D3Core"; // D3Core-1.7.10-1.1.0.29.jar
        public static final String DARK_MENAGERIE = "menagerie"; // dark_menagerie-1.7.10-beta-3.1a.jar
        public static final String DEATHLY_HALLOWS = "dh"; // deathlyhallows-1.1.8.jar
        public static final String DEFAULT_CONFIGS = "defaultkeys"; // defaultkeys-1.3.4.jar
        public static final String DEFAULT_SERVER_LIST = "defaultserverlist"; // defaultserverlist-1.7.4.jar
        public static final String DEFAULT_WORLD_GENERATOR = "defaultworldgenerator"; // defaultworldgenerator-0.5.0.jar
        public static final String DENSE_ORES = "denseores"; // denseores-1.6.2.jar
        public static final String DUMMY_CORE = "DummyCore"; // DummyCore-1.21.0.jar
        public static final String DYNAMIC_SURROUNDINGS = "dsurround"; // DynamicSurroundings-1.7.10-1.0.6.4.jar
        public static final String ENCHIRIDION = "Enchiridion"; // Enchiridion2-2.0.3.jar
        public static final String ENCHIRIDION2 = "Enchiridion2"; // Enchiridion2-2.0.3.jar
        public static final String ENDER_CORE = "endercore"; // endercore-0.5.15.jar
        public static final String ENDER_STORAGE = "EnderStorage"; // EnderStorage-1.8.9.jar
        public static final String ENDER_ZOO = "EnderZoo"; // EnderZoo-1.3.6.jar
        public static final String ENDLESS_I_DS = "endlessids"; // endlessids-mc1.7.10-1.7.4.jar
        public static final String ENHANCED_LOOT_BAGS = "enhancedlootbags"; // EnhancedLootBags-1.3.5.jar
        public static final String ET_FUTURUM_REQUIEM = "etfuturum"; // etfuturum-2.6.64-GTNH.jar
        public static final String EUPHORIA_PATCHER = "euphoria_patcher"; // EuphoriaPatcher-1.10.5-r5.9.3-forge1.7.10.jar
        public static final String EXTRA_BEES = "ExtraBees"; // binnie-mods-2.6.51.jar
        public static final String EXTRA_TREES = "ExtraTrees"; // binnie-mods-2.6.51.jar
        public static final String EXTREME_SOUND_MUFFLER_LEGACY = "extremesoundmuffler"; // esm-legacy-1.7.10-1.1.1.jar
        public static final String FAIRY_LIGHTS = "fairylights"; // fairylights-1.4.0-1.7.10.jar
        public static final String FALSE_PATTERN_LIB = "falsepatternlib"; // falsepatternlib-mc1.7.10-1.12.2.jar
        public static final String FARSEEK = "farseek"; // Farseek-1.7.10-2.0.1.jar
        public static final String FETHER = "fether"; // fether-2.0.9.jar
        public static final String FIND_IT = "findit"; // findit-1.4.8.jar
        public static final String FORBIDDEN_MAGIC = "ForbiddenMagic"; // Forbidden.Magic-0.9.18-GTNH.jar
        public static final String FORESTRY = "Forestry"; // Forestry-4.11.40.jar
        public static final String FORGELIN = "forgelin"; // forgelin-2.0.3-GTNH.jar
        public static final String FORGE_MICROBLOCKS = "ForgeMicroblock"; // ForgeMultipart-1.7.18.jar
        public static final String FORGE_MULTIPART = "ForgeMultipart"; // ForgeMultipart-1.7.18.jar
        public static final String FORGE_RELOCATION = "ForgeRelocation"; // ForgeRelocation-0.3.7.jar
        public static final String FORGOTTEN_RELICS = "ForgottenRelics"; // ForgottenRelics-1.7.3-[Final].jar
        public static final String GADOMANCY = "gadomancy"; // gadomancy-1.5.17.jar
        public static final String GARDEN_CONTAINERS = "GardenContainers"; // GardenStuff-1.7.10-1.7.0.jar
        public static final String GARDEN_CORE = "GardenCore"; // GardenStuff-1.7.10-1.7.0.jar
        public static final String GARDEN_STUFF = "GardenStuff"; // GardenStuff-1.7.10-1.7.0.jar
        public static final String GARDEN_TREES = "GardenTrees"; // GardenStuff-1.7.10-1.7.0.jar
        public static final String GENETICS = "Genetics"; // binnie-mods-2.6.51.jar
        public static final String GREGORY_TWEAKS_FOR_CRAFTING = "craftingtweaks"; // craftingtweaks-1.0.3.jar
        public static final String GROWTHCRAFT = "Growthcraft"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_APPLES = "Growthcraft|Apples"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_BAMBOO = "Growthcraft|Bamboo"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_BEES = "Growthcraft|Bees"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_CELLAR = "Growthcraft|Cellar"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_FISHTRAP = "Growthcraft|Fishtrap"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_GRAPES = "Growthcraft|Grapes"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_HOPS = "Growthcraft|Hops"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_MILK = "Growthcraft|Milk"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GROWTHCRAFT_RICE = "Growthcraft|Rice"; // growthcraft-1.7.10-2.7.3-complete.jar
        public static final String GTNH_EXT_LIB = "gtnhextlib"; // gtnhextlib-1.0.5.jar
        public static final String GTNH_LIB = "gtnhlib"; // gtnhlib-0.11.54.jar
        public static final String GUIDE_API = "guideapi"; // Guide-API-1.7.10-1.0.1-20.jar
        public static final String HAMMERZ = "hammerz"; // Hammerz-1.7.10-0.1.7.37.jar
        public static final String HARDCORE_ENDER_EXPANSION = "HardcoreEnderExpansion"; // HardcoreEnderExpansion-1.12.29-GTNH.jar
        public static final String HELP_FIXER = "HelpFixer"; // HelpFixer-1.3.2.jar
        public static final String HODGE_PODGE = "hodgepodge"; // hodgepodge-2.7.221.jar
        public static final String HOLO_INVENTORY = "holoinventory"; // holoinventory-2.5.18-GTNH.jar
        public static final String HOOK_LIB_ULTIMATE = "hooklibultimate"; // HookLibUltimate-1.0-universal.jar
        public static final String HOPPER_DUCTS = "HopperDuctMod"; // hopperductmod-1.7.10-1.3.2.jar
        public static final String INVENTORY_BOGO_SORTER = "bogosorter"; // bogosorter-1.3.55-GTNH.jar
        public static final String IN_GAME_INFO_XML = "InGameInfoXML"; // InGameInfoXML-2.9.8.jar
        public static final String IRON_CHESTS = "IronChest"; // IronChest-6.1.13.jar
        public static final String IRON_CHESTS_MINECARTS = "ironchestminecarts"; // IronChestMinecarts-1.2.1.jar
        public static final String IV_TOOLKIT = "IvToolkit"; // IvToolkit-1.2.1.jar
        public static final String JABBA = "JABBA"; // JABBA-1.5.24.jar
        public static final String JOURNEY_MAP = "journeymap"; // journeymap-forge-1.7.10-6.0.8.jar
        public static final String LITTLE_TILES = "littletiles"; // littletiles-1.6.63.jar
        public static final String LOOT_GAMES = "lootgames"; // lootgames-2.2.14.jar
        public static final String LOST_BOOKS = "LostBooks"; // LostBooks-1.7.10-1.2.2.jar
        public static final String LUNATRIUS_CORE = "LunatriusCore"; // LunatriusCore-1.2.1-GTNH.jar
        public static final String LWJGL3IFY = "lwjgl3ify"; // lwjgl3ify-3.0.42.jar
        public static final String MAGIA_NATURALIS = "magianaturalis"; // MagiaNaturalis-0.5.0.jar
        public static final String MAGICAL_CROPS = "magicalcrops"; // magicalcrops-1.7.10_0.1.jar
        public static final String MAGICAL_DECORATIONS = "mdeco"; // MagicalDecorations-0.3.5.jar
        public static final String MAGIC_BEES = "MagicBees"; // magicbees-2.10.13-GTNH.jar
        public static final String MALISIS_CORE = "malisiscore"; // malisisdoors-1.19.13-GTNH.jar
        public static final String MALISIS_DOORS = "malisisdoors"; // malisisdoors-1.19.13-GTNH.jar
        public static final String MC_FRAMES = "MCFrames"; // ForgeRelocation-0.3.7.jar
        public static final String MINE_AND_BLADE_BATTLE_GEAR2 = "battlegear2"; // battlegear2-1.6.8-backhand.jar
        public static final String MMM_MMM_MMM_MMM = "testdummy"; // MmmMmmMmmMmm-1.7.10-1.9.jar
        public static final String MOBS_INFO = "mobsinfo"; // mobsinfo-0.6.2.jar
        public static final String MODERN_MARKINGS = "modernmarkings"; // modernmarkings-0.3.13-1.7.10.jar
        public static final String MODULAR_UI2 = "modularui2"; // modularui2-2.3.94-1.7.10.jar
        public static final String MOD_TWEAKER = "modtweaker2"; // ModTweaker2-0.12.0.jar
        public static final String MORE_CHISELS = "morechisels"; // MoreChisels-1.7.10-1.0-28.jar
        public static final String MOUSE_TWEAKS = "MouseTweaks"; // MouseTweaks-2.5.4-GTNH.jar
        public static final String MR_TJP_CORE = "MrTJPCoreMod"; // MrTJPCore-1.3.7.jar
        public static final String MY_CTM_LIB = "MyCTMLib"; // MyCTMLib-1.4.2.jar
        public static final String NATURA = "Natura"; // Natura-2.8.24.jar
        public static final String NATURES_COMPASS = "naturescompass"; // naturescompass-1.5.2-GTNH.jar
        public static final String NAVIGATOR = "navigator"; // navigator-1.1.10.jar
        public static final String NEAT = "Neat"; // Neat+1.0-1.jar
        public static final String NECROMANCY = "necromancy"; // necromancy-NO-GIT-TAG-SET.jar
        public static final String NEI_ADDONS = "NEIAddons"; // NEIAddons-1.18.8.jar
        public static final String NEI_INTEGRATION = "neiintegration"; // NEIIntegration-1.5.3.jar
        public static final String NEMEX_LIB = "NemexLib"; // NemexLib-1.4.jar
        public static final String NETHERLICIOUS = "netherlicious"; // netherlicious-3.2.8.jar
        public static final String NETHER_PORTAL_FIX = "netherportalfix"; // netherportalfix-1.4.0.jar
        public static final String NODAL_MECHANICS = "NodalMechanics"; // NodalMechanics-.1.7.10.-1.1.0-K.jar
        public static final String NOT_ENOUGH_ITEMS = "NotEnoughItems"; // NotEnoughItems-2.8.171-GTNH.jar
        public static final String NOVA_CRAFT = "nova_craft"; // NovaCraft V1.5.1 (1.7.10).jar
        public static final String NO_HOTBAR_NEEDED = "nohotbarneeded"; // nohotbarneeded-0.0.6.jar
        public static final String NUTRITION = "nutrition"; // nutrition-1.0.1.jar
        public static final String OPEN_BLOCKS = "OpenBlocks"; // OpenBlocks-1.12.22-GTNH.jar
        public static final String OPEN_MODS_CORE = "OpenModsCore"; // OpenModsLibs-0.10.15.jar
        public static final String OPEN_MODS_LIB = "OpenMods"; // OpenModsLibs-0.10.15.jar
        public static final String ORE_EXCAVATION = "oreexcavation"; // OreExcavation-1.1.134.jar
        public static final String ORE_TWEAKER = "oretweaker"; // OreTweaker-1.0.2-1.7.10.jar
        public static final String ORIGIN = "origin"; // Origin-1.7.10-8.0.3.jar
        public static final String OVERLOADED_ARMOR_BAR = "overloadedarmorbar"; // overloadedarmorbar-1.1.1.jar
        public static final String O_AUTH = "oauth"; // oauth-1.3.4-GTNH.jar
        public static final String PAMS_HARVEST_CRAFT = "harvestcraft"; // Pam's HarvestCraft 1.7.10Lb.jar
        public static final String PLACEABLE_ITEMS_MOD = "placeableitems"; // placeableitems-2.0.1.jar
        public static final String PLATFORMS = "platforms"; // platforms-1.7.10-0.1.2.jar
        public static final String POSTEA = "postea"; // postea-1.2.7.jar
        public static final String PROJECT_RED_COMPATIBILITY = "ProjRed|Compatibility"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_CORE = "ProjRed|Core"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_EXPANSION = "ProjRed|Expansion"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_EXPLORATION = "ProjRed|Exploration"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_FABRICATION = "ProjRed|Fabrication"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_ILLUMINATION = "ProjRed|Illumination"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_INTEGRATION = "ProjRed|Integration"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_TRANSMISSION = "ProjRed|Transmission"; // ProjRed-4.12.50-GTNH.jar
        public static final String PROJECT_RED_TRANSPORTATION = "ProjRed|Transportation"; // ProjRed-4.12.50-GTNH.jar
        public static final String QUEST_BOOK = "questbook"; // BetterQuesting-3.8.92-GTNH.jar
        public static final String RANDOM_BOUBLES = "randomboubles"; // randomboubles-1.1.7.jar
        public static final String RANDOM_THINGS = "RandomThings"; // RandomThings-2.7.11.jar
        public static final String REALISTIC_WORLD_GEN2 = "rwg2"; // rwg2-2.0.6.jar
        public static final String RELIQUARY = "xreliquary"; // Reliquary-1.2.jar
        public static final String RELOCATION_FMP = "RelocationFMP"; // ForgeRelocationFMP-0.2.0.jar
        public static final String ROGUELIKE_DUNGEONS = "Roguelike"; // roguelike-1.6.6-GTNH.jar
        public static final String RUINS = "AS_Ruins"; // Ruins-NH_fork+modified.jar
        public static final String RWG = "RWG"; // RWG-alpha-1.5.3.jar
        public static final String SALIS_ARCANA = "salisarcana"; // salisarcana-v2.7.0.jar
        public static final String SANGUIMANCY = "Sanguimancy"; // Sanguimancy-1.7.10-1.1.10-LE.jar
        public static final String SANGUIMANCY_PATCH = "SanguimancyPatch"; // SanguimancyPatch-1.1.0.jar
        public static final String SANGUINE_UTILITIES = "SanguineExtras"; // Sanguine Utilities-1.3.2.jar
        public static final String SAN_ANDREAS_PS_MANAGER_PACK = "sapmanpack"; // SAPManPack-1.7.10-2.7.2.jar
        public static final String SCHEMATICA = "Schematica"; // Schematica-1.12.6-GTNH.jar
        public static final String SERVER_UTILITIES = "serverutilities"; // ServerUtilities-2.4.22.jar
        public static final String SHARE_WHERE_I_AM = "sharewhereiam"; // sharewhereiam-2.1.11.jar
        public static final String SHETI_PHIAN_CORE = "ShetiPhianCore"; // ShetiPhianCore-1.7.10-3.0.0.jar
        public static final String SIMPLE_SKIN_BACKPORT = "simpleskinbackport"; // simpleskinbackport-1.0.5-GTNH.jar
        public static final String SLEEPING_BAG = "sleepingbag"; // sleepingbag-0.3.1.jar
        public static final String SNAD = "snad"; // Snad-1.7.10-1.6.01.31a.jar
        public static final String SOUND_FILTERS = "soundfilters"; // SoundFilters-0.8_for_1.7.X.jar
        public static final String SPECIAL_MOBS = "SpecialMobs"; // SpecialMobs-3.7.10.jar
        public static final String SPICE_OF_LIFE = "SpiceOfLife"; // SpiceOfLife-2.2.12-carrot.jar
        public static final String STORAGE_DRAWERS = "StorageDrawers"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STORAGE_DRAWERS_BIOMES_O_PLENTY_PACK = "StorageDrawersBop"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STORAGE_DRAWERS_EREBUS_PACK = "StorageDrawersErebus"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STORAGE_DRAWERS_FORESTRY_PACK = "StorageDrawersForestry"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STORAGE_DRAWERS_MISC_PACK = "StorageDrawersMisc"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STORAGE_DRAWERS_NATURA_PACK = "StorageDrawersNatura"; // StorageDrawers-2.2.31-GTNH.jar
        public static final String STREAMS = "streams"; // Streams-1.7.10-0.3.4.jar
        public static final String STRUCTURE_COMPAT = "structurecompat"; // structurecompat-0.7.5.jar
        public static final String STRUCTURE_LIB = "structurelib"; // structurelib-1.4.47.jar
        public static final String SUPER_CRAFTING_FRAME = "supercraftingframe"; // supercraftingframe-1.7.10.3.jar
        public static final String TAILS_LEGACY = "tailslegacy"; // TailsLegacy-4.0.3+1.7.10-forge.jar
        public static final String TAINTED_MAGIC = "TaintedMagic"; // Tainted-Magic-7.7.8-GTNH.jar
        public static final String TALISMANS2 = "Talismans 2"; // TalismansFixed2.jar
        public static final String TC4_TWEAKS = "tc4tweak"; // Thaumcraft4Tweaks-1.5.48.jar
        public static final String TC_NODE_TRACKER = "tcnodetracker"; // tcnodetracker-1.4.6.jar
        public static final String THAUMATURGICAL_KNOWLEDGE = "benway_knowledge"; // Thaumaturgical Knowledge-0.1.5.2 -
                                                                                  // 1.7.10.jar
        public static final String THAUMCRAFT = "Thaumcraft"; // Thaumcraft-1.7.10-4.2.3.5.jar
        public static final String THAUMCRAFT_MOB_ASPECTS = "ThaumcraftMobAspects"; // ThaumcraftMobAspects-1.2.1-GTNH.jar
        public static final String THAUMCRAFT_RESEARCH_TWEAKS = "ThaumcraftResearchTweaks"; // ThaumcraftResearchTweaks-1.4.1.jar
        public static final String THAUMIC_ALCHEMY = "thaumicalchemy"; // Thaumic-Alchemy-.1.7.10.1.2-K-0.jar
        public static final String THAUMIC_BASES = "thaumicbases"; // Thaumic-Based-1.9.22.jar
        public static final String THAUMIC_BOOTS = "thaumicboots"; // thaumicboots-1.5.13.jar
        public static final String THAUMIC_DARKNESS = "ThaumicDarkness"; // ThaumicDarkness-1.7.2-1.3.1.jar
        public static final String THAUMIC_DYES = "thaumicdyes"; // thaumicdyes-1.10.11-JB.jar
        public static final String THAUMIC_EXPLORATION = "ThaumicExploration"; // Thaumic-Exploration-1.5.29-GTNH.jar
        public static final String THAUMIC_HORIZONS = "ThaumicHorizons"; // ThaumicHorizons-1.8.28.jar
        public static final String THAUMIC_INSURGENCE = "thaumicinsurgence"; // thaumicinsurgence-0.4.1.jar
        public static final String THAUMIC_MACHINA = "ThaumicMachina"; // ThaumicMachina-0.2.6-GTNH.jar
        public static final String THAUMIC_RENAISSANCE = "TCReborn"; // Thaumic_Renaissance-1.5.2.2.jar
        public static final String THAUMIC_TINKERER = "ThaumicTinkerer"; // ThaumicTinkerer-2.12.34.jar
        public static final String TOO_MUCH_LOOT = "TML"; // TooMuchLoot-4.3.0-GTNH.jar
        public static final String TOTEMIC = "totemic"; // Totemic-1.7.10-0.7.1.jar
        public static final String TRANSLOCATOR = "Translocator"; // Translocator-1.4.5.jar
        public static final String TRAVELLERS_GEAR = "TravellersGear"; // TravellersGear-1.7.10-1.16.6.jar
        public static final String TRAVELLERS_GEAR_NEO = "travellersgearneo"; // travellersgearneo-1.2.4.jar
        public static final String TWILIGHT_FOREST = "TwilightForest"; // TwilightForest-2.7.45.jar
        public static final String TX_LOADER = "txloader"; // txloader-1.9.1.jar
        public static final String UNI_LIB = "unilib"; // UniLib-1.2.1+1.7.10-forge.jar
        public static final String UNI_MIXINS = "unimixins"; // +unimixins-all-1.7.10-0.3.1.jar
        public static final String VILLAGERS_NOSE = "VillagersNose"; // VillagersNose-1.7.10-1.3c.jar
        public static final String VILLAGE_NAMES = "VillageNames"; // VillageNames-4.5.21-GTNH.jar
        public static final String WAILA = "Waila"; // Waila-1.19.34.jar
        public static final String WAILA_HARVESTABILITY = "WailaHarvestability"; // WailaHarvestability-1.3.6-GTNH.jar
        public static final String WAILA_PLUGINS = "wailaplugins"; // WAILAPlugins-0.7.2.jar
        public static final String WANION_LIB = "wanionlib"; // WanionLib-1.10.1.jar
        public static final String WARP_THEORY = "WarpTheory"; // WarpTheory-1.5.8-GTNH.jar
        public static final String WAYSTONES_X = "waystones"; // waystones-1.0.16.jar
        public static final String WHAT_ARE_WE_LOOKING_AT = "wawla"; // Wawla-1.3.4-GTNH.jar
        public static final String WITCHERY = "witchery"; // witchery-1.7.10-0.24.1.jar
        public static final String WITCHERY_EXTRAS = "WitcheryExtras"; // WitcheryExtras-1.4.20.jar
        public static final String WITCHING_GADGETS = "WitchingGadgets"; // WitchingGadgets-AdvScribeFix.jar
        public static final String WOLF_ARMOR_AND_STORAGE = "wolfarmor"; // wolfarmor-1.3.0.1.jar
        public static final String YAM_CORE = "YAMCore"; // YAMCore-0.7.5.jar
        public static final String ZUME = "zume"; // zume-1.2.2.jar
        public static final String Z_TONES = "Ztones"; // Ztones-2.3.3-GTNH.jar
    }
}
