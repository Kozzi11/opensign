/**
 This file is part of OpenSign.

 OpenSign is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; either version 2.1 of the License, or
 (at your option) any later version.

 OpenSign is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with OpenOcesAPI; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


 Note to developers:
 If you add code to this file, please take a minute to add an additional
 copyright statement above and an additional
 @author statement below.
 @author sdj sdj@miracleas.dk
 */
package org.openoces.opensign.pdf.validator.v200;

import java.util.Arrays;
import java.util.HashSet;


public class Constants {

	static HashSet<String> KNOWNBINARYTYPE = new HashSet<String>(Arrays.asList(
			new String[] {"/Image","/CIDFontType0C","/CIDFontType0","/OpenType","/Type1","/MMType1","/CIDFontType2","/TrueType"}));
	
	/*static	HashSet<String> FILTERS = new HashSet<String>(Arrays.asList(
			new String[] { "/ASCIIHexDecode","/AHx","/ASCII85Decode","/A85","/FlateDecode","/Fl","/LZWDecode","/LZW","/RunLengthDecode","/RL",
					"/CCITTFaxDecode","/CCF","/DCTDecode","/DCT","/JBIG2Decode","/JPXDecode","/Crypt"}));
      */
	static	HashSet<String> WHITELIST = new HashSet<String>(Arrays.asList(
			new String[] {
					/*****
					 * 
					 * Syntax
					 * 
					 */

					"/null",

					/*entries common to all stream dictionaries*/		
					/*table 3.4*/
					"/Length","/Filter","/DecodeParams","/DL",	

					/* Standard filters */
					/* Table 3.5 */	
					"/ASCIIHexDecode","/AHx","/ASCII85Decode","/A85","/FlateDecode","/Fl","/LZWDecode","/LZW","/RunLengthDecode","/RL","/CCITTFaxDecode","/CCF","/JBIG2Decode","/DCTDecode","/DTC","/JPXDecode","/Crypt",

					/*optional parameters for LZWDecode and FlateDecode filters*/
					/*table 3.7*/
					"/Predictor","/Colors","/BitsPerComponent","/Columns","/EarlyChange",

					/*optional parameters for the CCITTFaxDecode*/
					/*table 3.9*/
					"/K","/EndOfLine","/EncodedByteAlign","/Columns","/Rows","/EndOfBlock","/Blackls1","/DamagedRowsBeforeError",

					/*optional parameter for the JBIG2Decode filter*/
					/*table 3.10*/
					"/JBIG2Globals",

					/*optional parameter for the DCTDecode filter*/
					/*table 3.11*/
					"/ColorTransform",
					
					/*Optional paramters for Crypt filters*/
					/*Table 3.12*/
					"/Type","/CryptFilterDecodeParms","/Name",
					
					/*Entries in the file trailer dictionary*/
					/*table 3.13*/
					"/Size","/Prev","/Root","/Encrypt","/Info","/ID",

					/*Additional entries specific to an object stream dictionary*/
					/*Table 3.14*/
					"/Type","/ObjStm","/N","/First","/Extends",

					/*Additional entries specific to a cross-reference stream dictionary*/
					/*Table 3.15*/
					"/Type","/XRef","/Size","/Index",
					"/Prev","/W",
					
					/*Additional entries in a hybrid-reference files trailer dictionary*/
					/*Table 3.17*/
					"/XRefStm",
					
					/*Entries common to all encryption dictionaries*/
					/*table 3.18*/
					"/Filter","/Standard","/SubFilter","/V","/Length","/CF","/StmF","/StrF","/EFF",

					/*additional encryption dictionary entries for the standard security handler*/
					/*table 3.19*/
					"/R","/O","/U","/P","/EncryptMetadata",

				    /*Additional encryption dictionary entries for public-key security handlers*/
					/*Table 3.21*/
					"/Receipients",

					/*Entries common to all crypt filter dictionaries*/
					/*Table 3.22*/
					"/Type","/CryptFilter",
					"/CFM","/None","/V2","/AESV2",
					"/AuthEvent","/DocOpen","/EFOpen",
					"/Length",

					/*Standard crypy filter names*/
					/*Table 3.23*/
					"/Identify",

					/*Additional crypt filter dictionaries for entries for public-key security handlers*/
					/*Table 3.24*/
					"/Receipients","/EntcryptMetaData",
					
					
					/*Entries in the catalog dictionary*/
					/*table 3.25*/
					"/Type", "/Catalog",
					"/Version","/1.4","/1.1","/1.2","/1.3","/1.5","/1.6","/1.7",
					"/Pages","/PageLabels","/Names","/Dests","/ViewerPreferences","/PageLayout","/PageMode","/Outlines","/Threads",
					"/Metadata","/StructTreeRoot","/MarkInfo","/Lang","/OutputIntents",
					"/SinglePage","/OneColumn","/TwoColumnLeft","/TwoColumnRight","/TwoPageLeft","/TwoPageRight",
					"/UseNone","/UseOutlines","/UseThumbs","/FullScreen","/UseOC","/UseAttachments",
					"/PieceInfo","/OCProperties","/Perms","/Legal","/Requirements","/NeedsRendering",					
					
					/*added to 3.25 in Adobe supplement to ISO 32000*/
					"/Extensions",
					
					/*3.25b Entries in a developer extensions dictionary*/
					"/Type","/DeveloperExtensions","/BaseVersion","/1.5","/1.6","/1.7",
					"/ExtensionLevel", "/ADBE",
					
					/*required entries in a page node*/
					/*table 3.26*/
					"/Type", "/Pages","/Parent","/Kids","/Count",

					/*Entries in a page object*/
					/*table 3.27*/
					"/Type","/Page","/Parent","/LastModified","/Resources","/MediaBox","/CropBox","/BleedBox",
					"/TrimBox","/ArtBox","/BoxColorInfo","/Contents","/Rotate","/Group","/Thumb","/B","/Trans","/Annots","/Metadata","/PieceInfo",
					"/StructParents","/ID","/PZ","/SeparationInfo",
					"/Tabs","/R","/C","/S","/TemplateInstantianted","/PresSteps","/UserUnit","/VP",


					/*entries in the name dictionary*/
					/*table 3.28*/
					"/Dests","/AP","/Pages","/Templates","/IDS","/AlternatePresentations","/Renditions",
					"/XFAResources",

					

					/*entries in a resource dictionary*/
					/*table 3.30*/
					"/ExtGState","/ColorSpace","/Pattern","/Shading","/XObject","/Font","/ProcSet","/Properties",

					/*Entries in a name tree node dictionary*/
					/*table 3.33*/
					"/Kids","/Names","/Limits",

					/*Entries in a number tree node dictionary*/
					/*table 3.34*/
					"/Kids","/Nums","/Limits",

					/*Entries common to all function dictionary*/
					/*Table 3.35*/
					"/FunctionType","/Domain","/Range",

					/*additional entries specific to a type 0 function dictionary*/
					/*table 3.36*/
					"/Size","/BitsPerSample","/Order","/Encode","/Decode",

					/*Additional entries specific to a type 2 function dictionary*/
					/*Table 3.37*/
					"/C0","/C1","/N",

					/*Additional entries specific to a type 3 function dictionary*/
					/*Table 3.29*/
					"/Functions","/Bounds",	"/Encode",

					/*Entries in a collection item dictionary*/
					/*Table 3.45*/
					"/Type","/CollectionItem",


					/*Entries in a collection subitem dictionary*/
					/*Table 3.46*/
					"/Type","/CollectionSubItem","/D","/P",

					
					/*****
					 * 
					 * Graphics
					 * 
					 */

					/*Entries in graphics state parameter dictionary*/
					/*Table 4.8*/
					"/Type","/ExtGState",
					"/LW","/LC",
					"/LJ","/ML",
					"/D","/RI",
					"/OP","/op",
					"/OPM","/Font",
					"/BG","/BG2",
					"/UCR","/UCR2",
					"/TR","/TR2",
					"/HT","/FL",
					"/SM","/SA",
					"/BM","/SMask",
					"/CA","/ca",
					"/AIS",	"/TK",

					/*Color space families*/
					/*Table 4.12*/
					"/DeviceGray","/DeviceRGB","/DeviceCMYK","/CalGray","/CalRGB","/Lab","/ICCBased","/Indexed","/Pattern","/Separation","/DeviceN",

					/*Entries in a CalGray color space dictionary*/
					/*Table 4.13*/
					"/WhitePoint",
					"/BlackPoint",
					"/Gamma",

					/*Entries in a CalRGB color space dictionary*/
					/*Table 4.14*/
					"/WhitePoint","/BlackPoint","/Gamma","/Matrix",

					/*Entries in a Lab color space dictionary*/
					/*Table 4.15*/
					"/WhitePoint","/BlackPoint","/Range",

					/*Additional entries specific to an ICC profile dictionary*/
					/*Table 4.16*/
					"/N","/Alternate","/Range","/Metadata",

					/*Rendering Intents*/
					/*Table 4.20*/
					"/AbsoluteColorimetric","/RelativeColorimetric","/Saturation","/Perceptual",

					/*Entry in a DeviceN color space attributes dictionary*/
					/*Table 4.20*/
					"/Colorants",

					/*Entries in a DeviceN color space attributes dictionary*/
					/*Table 4.21*/
					"/Subtype","/DeviceN","/NChannel","/Colorants","/Process","/MixingHints",

					/*Entries in a DeviceN process dictionary*/
					/*Table 4.22*/
					"/ColorSpace","/Components",

					/*Entries in DeviceN mixing hints dictionary*/
					/*Table 4.23*/
					"/Solidities","/PrintingOrder","/DotGain",

					/*Additional entries specific to a type 1 pattern dictionary*/
					/*Table 4.25*/
					"/Type","/PatternType","/PaintType","/TilingType","/BBox","/XStep","/YStep","/Resources","/Matrix",

					/*Entries in a type 2 pattern dictionary*/
					/*Table 4.26*/
					"/Type","/PatternType","/Shading","/Matrix","/ExtGState",

					/*Entries common to all shading dictionaries*/
					/*Table 4.28*/
					"/ShadingType","/ColorSpace","/Background","/BBox","/AntiAlias",

					/*Additional entries specific to a type 1 shading dictionary*/
					/*Table 4.29*/
					"/Domain","/Matrix","/Function",

					/*Additional entries specific to a type 2 shading dictionary*/
					/*Table 4.30*/
					"/Coords","/Domain","/Function","/Extend",

					/*Additional entries specific to a type 3 shading dictionary*/
					/*Table 4.31*/
					"/Coords","/Domain","/Function","/Extend",

					/*Additional entries specific to a type 4 shading dictionary*/
					/*Table 4.32*/
					"/BitsPerCoordinate","/BitsPerComponent","/BitsPerFlag","/Decode","/Function",

					/*Additional entries specific to a type 5 shading dictionary*/
					/*Table 4.33*/
					"/BitsPerCoordinate","/BitsPerComponent","/VerticesPerRow","/Decode","/Function",

					/*Additional entries specific to a type 6 shading dictionary*/
					/*Table 4.34*/
					"/BitsPerCoordinate","/BitsPerComponent","/BitsPerFlag","/Decode","/Function",

					/*Additional entries specific to a PostScript XObject dictionary*/
					/*Table 4.38 */
					"/Type","/XObject",	"/Subtype","/PS","/Level1",
					
					/*Additional entries specific to an image dictionary*/
					/*Table 4.39*/
					"/Type","/XObject","/Subtype","/Image","/Width","/Height","/ColorSpace",
					"/BitsPerComponent","/Intent","/ImageMask","/Mask","/SMask","/SMaskInData",
					"/Decode","/Interpolate","/Alternates","/Name","/StructParent","/ID","/OPI",
					"/Metadata","/OC",

					/*Entries in an alternate image dictionary*/
					/*Table 4.41*/
					"/Image","/DefaultForPrinting","/OC",

					/*Entries in a inline image object*/
					/*Table 4.43*/
					"/BitsPerComponent","/ColorSpace","/Decode","/DecodeParms","/Filter","/Height","/ImageMask","/InterPolate","/Width",

					/*Additional abbreviations in an inline image object*/
					/*Table 4.44*/
					"/DeviceGray","/DeviceRGB","/DeviceCMYK","/Indexed","/ASCIIHexDecode","/ASCII85Decode","/LWZDecode","/FlatDecode","/RunLengthDecode","/CCITTFaxDecode","/DCTDecode",

					/*Additional entries specific to a type 1 form dictionary*/
					/*Table 4.45*/
					"/Type","/XObject",
					"/Subtype",	"/Form",
					"/FormType","/BBox",
					"/Maxtrix",	"/Resources",
					"/Group","/Ref",
					"/Metadata","/PieceInfo",
					"/LastModified","/StructParent",
					"/StructParents","/OPI",
					"/OC","/Name",

					
					/*Entries common to all group attributes dictionary*/
					/*Table 4.46*/
					"/Type","/Group","/S","/Transparency",

					/*Entries in an optional content group dictionary*/
					/*Table 4.48*/
					"/Type","/OCG","/Name","/Intent","/Usage",

					/*Entries in an optional content membership dictionary*/
					/*Table 4.49*/
					"/Type","/OCMD",
					"/OCGs","/P","/AllOn","/AnyOn","/AnyOff","/AllOff","/VE",

					/*Entries in the optional content properties dictionary*/
					/*Table 4.50*/
					"/OCGs","/D","/Configs",
					
					/*Entries in an optional content configuration dictionary*/
					/*Table 4.51*/
					"/Name","/Creator",
					"/BaseState","/ON","/OFF","/Unchanged",
					"/ON","/OFF","/Intent","/View","/Design","/All",
					"/AS","/Order","/ListMode","/AllPages","/VisiblePages",
					"/RBGroups","/Locked",

					/*Entries in an optional content usage dictionary*/
					/*Table 4.52*/
					"/CreatorInfo","/Language","/Export","/Zoom","/Print","/View",
					"/User","/PageElement",	"/Lang","/Preferred","/ExportState",
					"/min","/max","/Subtype","/Trapping","/PrintersMarks",
					"/Watermark","/ViewState","/Ind","/Ttl","/Org","/HF","/FG",
					"/BG","/L",
					
					/*****
					 * 
					 * Text
					 * 
					 */

					/*Font types*/
					/*table 5.7*/
					"/Type0","/Type1","/MMType1","/Type3","/TrueType","/CIDFontType0","/CIDFontType2",

					/*Entries in a Type 1 font dictionary*/
					/*Table 5.8*/
					"/Type","/Font","/SubType","/Type1","/Name","/BaseFont","/FirstChar","/LastChar","/Widths","/FontDescriptor","/Encoding","/ToUnicode",
					"/MacRomanEncoding","/MacExpertEncoding","/WinAnsiEncoding",

					/*standard fonts*/
					"/Times-Roman",
					"/Times-Bold",
					"/Times-Italic",
					"/Times-BoldItalic",
					"/Helvetica",
					"/Helvetica-Bold",
					"/Helvetica-Oblique",
					"/Helvetica-BoldOblique",
					"/Courier",
					"/Courier-Bold",
					"/Courier-Oblique",
					"/Courier-BoldOblique",
					"/Symbol",
					"/ZapfDingbats",

					/*Entries in a type 3 font dictionary*/
					/*Table 5.9*/
					"/Type","/Font","/Subtype","/Type3","/Name","/FontBBox",
					"/FontMatrix","/CharProcs","/Encoding","/FirstChar",
					"/LastChar","/Widths","/FontDescriptor","/Resources","/ToUnicode",

					/*Entries in an encoding dictionary*/
					/*Table 5.11*/
					"/Type","/Encoding","/BaseEncoding","/Differences",
					"/MacRomanEncoding","/MacExpertEncoding","/WinAnsiEncoding",

					/*Entries in a CIDSystemInfo dictionary*/
					/*Table 5.13*/
					"/Registry","/Ordering","/Supplement",

					/*Entries in a CIDFont dictionary*/
					/*Table 5.14*/
					"/Type","/Font","/Subtype","/CIDFontType0","/CIDFontType2","/BaseFont","/CIDSystemInfo","/FontDescriptor","/DW","/W","/DW2","/W2","/CIDToGIDMap",

					/*Predefined CJK CMap names*/
					/*Table 5.15*/
					"/BG-EUC-H","/BG-EUC-V","/GBpc-EUC-H","/GBpc-EUC-V",
					"/GBK-EUC-H","/GBK-EUC-V","/GBKp-EUC-H","/GBKp-EUC-H",
					"/GBK2K-H","/GBK2K-V","/UniGB-UCS2-H","/UniGB-UCS2-V",
					"/UniGB-UTF16-H","/UniGB-UTF16-V","/B5pc-H","/B5pc-V",
					"/HKscs-B5-H","/HKscs-B5-V","/ETen-B5-H","/ETen-B5-V",
					"/ETenms-B5-H","/ETenms-B5-V","/CNS-EUC-H","/CNS-EUR-V",
					"/UniCNS-UCS2-H","/UniCNS-UCS2-V","/UniCNS-UTF16-H",
					"/UniCNS-UTF16-V","/83pv-RKSJ-H","/90ms-RKSJ-H","/90ms-RKSJ-V",
					"/90msp-RKSJ-H","/90msp-RKSJ-V","/90pv-RKSJ-H","/Add-RKSJ-H",
					"/Add-RKSJ-V","/EUC-H","/EUC-V","/Ext-RKSJ-H","/Ext-RKSJ-V",
					"/UniJIS-UCS2-H","/UniJIS-UCS2-V","/UniJIS-UCS2-HW-H",
					"/UniJIS-UCS2-HW-V","/UniJIS-UTF16-H","/UniJIS-UTF16-V",
					"/KSC-EUC-H","/KSC-EUC-V","/KSCms-UHC-H","/KSCms-UHC-V",
					"/KSCms-UHC-HW-H","/KSCms-UHC-HW-V","/KSCpc-EUC-H","/UniKS-UCS2-H",
					"/UniKS-UCS2-V","/UniKS-UTF16-H","/UniKS-UTF16-V","/Identify-H",
					"/Identify-V", 

					
					/*Additional entries in a CMAP dictionary*/
					/*Table 5.17*/
					"/Type","/CMap","/CMapName","/CIDSystemInfo","/WMode","/UseCMap",


					/*Type 0 font dictionary*/
					/*Table 5.18*/
					"/Type","/Font","/SubType","/Type0","/BaseFont","/Encoding","/DescendantFonts","/ToUnicode",

					/*Font descriptors*/
					/*table 5.19*/
					"/Type","/FontDescriptor","/FontName","/FontFamily","/FontStretch",
					"/FontWeight","/Flags","/FontBBox","/ItalicAngle",
					"/Ascent","/Descent","/Leading","/CapHeight",
					"/XHeight","/StemV","/StemH","/AvgWidth",
					"/MaxWidth","/MissingWidth","/FontFile","/FontFile2",
					"/FontFile3","/CharSet",

					/*Additional font descriptor entries for CIDFonts*/
					/*Table 5.21*/
					"/Style","/Lang","/FD","/CIDSet",

					/*Characterclasses in CJK fonts*/
					/*Table 5.22*/
					"/Alphabetic","/AlphaNum","/Dingbats","/DingbatsRot",
					"/Generic","/GenericRot","/HKana","/HKanaRot",
					"/Hanzi","/HRoman","/HRomanRot","/Kana",
					"/Kanji","/Proportional","/ProportionalRot","/Ruby",
					"/HojoKanji",


					/*Embedded font orginization for various font types*/
					/*Table 5.23*/
					"/Type1C","/CIDFontType0C","/OpenType",

					/*Additional entries in a embedded font stream dictionary*/
					/*Table 5.24*/
					"/Length1","/Length2","/Length3","/Subtype","/Metadata",

					/*******
					 * 
					 * RENDERING
					 * 
					 */

					/*Predefined spot functions*/
					/*Table 6.1*/
					"/SimpleDot","/InvertedSimpleDot","/DoubleDot","/InvertedDoubleDot",
					"/CosineDot","/Double","/InvertedDouble","/Line","/LineX",
					"/LineY","/Round","/Ellipse","/EllipseA","/InvertedEllipseA",
					"/EllipseB","/EllipseC","/InvertedEllipseC","/Square","/Cross","/Rhombold",
					"/Diamond",


					/*Entries in a type 1 halftone dictionary*/
					/*Table 6.3*/
					"/Type", "/Halftone","/HalftoneType","/HalftoneName","/Frequency",
					"/Angle","/SpotFunction","/AccurateScreens","/TransferFunction","/Identify",

					/*Additional entries specific to type 6 halftone*/
					/*Table 6.4*/
					"/Type","/HalftoneType","/HalftoneName","/Width","/Height","/TransferFunction"
					,"/Identify",

					/*Additional entries specific to type 10 halftone*/
					/*Table 6.5*/
					"/Type","/HalftoneType","/HalftoneName","/Xsquare","/Ysquare",
					"/TransferFunction","/Identify",

					/*Additional entries specific to type 16 halftone*/
					/*Table 6.6*/
					"/Type","/HalftoneType","/HalftoneName","/Width","/Height",
					"/Width2","/Height2","/TransferFunction","/Identify",

					/*Entries in a type 5 halftone dictionary*/
					/*table 6.7*/
					"/Type","/HalftoneType",
					"/HalftoneName",//		<any colorant name>
					"/Default",

					"/Red","/Green","/Blue","/Cyan","/Magenta","/Yellow","/Black","/Gray",

					/****
					 * 
					 * Transparency
					 *  
					 */

					/*Standard separable blend modes*/
					/*Table 7.2*/
					"/Normal","/Multiply","/Screen","/Overlay","/Darken","/Lighten",
					"/ColorDodge","/ColorBurn","/HardLight","/SoftLight",
					"/Difference","/Exclusion",

					/*Standard nonseparable blend modes*/
					/*Table 7.3*/
					"/Hue","/Saturation","/Color","/Luminosity",
					
					/*Entries in a soft-mask dictionary*/
					/*Table 7.10*/
					"/Type","/Mask","/S","/Alpha","/Luminosity","/G","/BC","/TR",

					/*Restriction in a soft image dictionary*/
					/*Table 7.11*/
					"/Type","/XObject","/Subtype","/Image",	"/Width","/Height",
					"/ColorSpace","/DeviceGray","/BitsPerComponent","/Intent",
					"/ImageMask","/Mask","/SMask","/Decode","/Interpolate",
					"/Alternates","/Name","/StructParent","/ID","/OPI",

					/*Additional entry in a soft-mask image dictionary*/
					/*Table 7.12*/
					"/Matte",

					/*Additional entry specific to a transparency group attributes dictionary*/
					/*table 7.13*/
					"/S","/Transparency","/CS","/I","/K",

					/****
					 * 
					 * Interactive features
					 * 
					 */

					/*Entries in a viewer preferences dictionary*/
					/*Table 8.1*/
					"/HideToolbar",	"/HideMenubar",
					"/HideWindowsUI","/FitWindow",
					"/CenterWindow","/DisplayDocTitle",
					"/NonFullScreenPageMode", 
					"/UseNone","/UseOutlines","/UseThumbs","/UseOC",
					"/Direction","/L2R","/R2L",
					"/ViewArea","/ViewClip","/PrintArea","/PrintClip",
					"/Simplex","/DuplexFlipShortEdge","/DuplexFlipLongEdge",
					"/PrintScaling","/None","/AppDefault",
					"/Duplex","/PickTrayByPDFSize","/PrintPageRange",
					"/NumCopies",
					
					/*Destination syntax*/
					/*Table 8.2*/
					"/XYZ","/Fit","/FitH","/FitV","/FitR","/FitB","/FitBH","/FitBV",


					/*Entries in the outline dictionary*/
					/*Table 8.3*/
					"/Type","/Outlines","/First","/Last","/Count",

					/*Entries in the outline item dictionary*/
					/*Table 8.4*/
					"/Title","/Parent","/Prev","/Next",
					"/First","/Last","/Count","/Dest",
					"/SE","/C",	"/F",

					/*Entries in a collection dictionary*/
					/*Table 8.6*/
					"/Type","/Collection","/Schema","/D","/View","/D","/T","/H","/Sort",

					/*Entries in a collection field dictionary*/
					/*Table 8.8*/
					"/Type","/CollectionField","/Subtype","/S","/D","/N",
					"/N","/O","/V","/E",

					/*Entries in a collection sort dictionary*/
					/*Table 8.9*/
					"/Type","/CollectionSort","/S","/A",
										
					/*Entries in a page label dicionary*/
					/*Table 8.10*/
					"/Type","/PageLabel","/S","/P",	"/St",

					/*Entries in a thread dictionary*/
					/*Table 8.11*/
					"/Type", "/Thread",	"/F","/I",

					/*Entries in a bead dictionary*/
					/*Table 8.12*/
					"/Type","/Bead","/T","/N","/V","/P","/R",

					/*Entries common to all annotation dictionaries*/
					/*Table 8.15*/
					"/Type","/Annot","/Subtype","/Contents","/P","/Rect","/NM","/M",
					"/F","/BS","/Border","/AP","/AS","/Contents","/CA","/T","/StructParent",
					"/OC",

					/*Annotation flags*/
					/*Table 8.16*/
					"/Invisible","/Hidden","/Print","/NoZoom","/NoRotate","/NoView",
					"/ReadOnly","/Locked","/ToggleNoView","/LockedContent",
					
					/*Entries in a border style dictionary*/
					/*Table 8.17/*/
					"/Type", "/Border",
					"/W","/S",
					"/D","/B","/I","/U",

					/*Entries in a border effect dictionary*/
					/*Table 8.18*/
					"/S","/C","/I",
					
					/*Entries in a appearance dictionary*/
					/*Table 8.19*/
					"/N","/R","/D",
					
					/*Annotation Types*/
					/*Table 8.20*/
					"/Text","/FreeText","/Line","/Square","/Circle","/Polygon",
					"/PolyLine","/Highlight","/Underline","/Squiggly","/StrikeOut",
					"/Stamp","/Caret","/Ink","/Popup","/PrinterMark","/TrapNet",
					"/Screen","/Watermark", "/Link",
					
					/*Additional entries specific to markup annotation*/
					/*Table 8.21*/
					"/T","/Popup","/CA","/RC","/CreationDate","/IRT","/Subj",
					"/RT","/IT","/ExData",
					
					/*Annotation states*/
					/*Table 8.22*/
					"/Marked","/Unmarked","/Accepted","/Rejected","/Cancelled",
					"/Completed","/None",

					/*Additional entries specific to a text annotation*/
					/*Table 8.23*/
					"/Subtype","/Text","/Open","/Name","/Comment",
					"/Help","/Insert","/Key","/NewParagraph","/Note","/Paragraph",

					/*Additional entries specific to a free text annotation*/
					/*table 8.25*/
					"/Subtype",	"/FreeText","/Content",	"/DA","/Q",

					/*Additional entries specific to a line annotation*/
					/*Table 8.26*/
					"/Subtype","/Line","/L","/BS","/LE","/IC",
					"/LL","/LLE","/Cap","/IT","/LLO","/CP","/Inline","/Top",
					"/Measure","/CO",
					
					/*Additional entries specific to a square or circle annotation*/
					/*Table 8.28*/
					"/Subtype","/Square","/Circle","/BS","/IC","/BE","/RD",

					/*Additional entries specific to a polygon or polyline annotation*/
					/*Table 8.29*/
					"/Subtype","/Polygon","/PolyLine","/Vertices","/LE","/BS","/IC",
					"/BE","/IT","/PolygonCloud","/PolyLineDimension","/PolygonDimension",
					"/Measure",

					/*Additional entries specific to markup annotations*/
					/*Table 8.30*/
					"/Subtype",	"/Highlight","/Underline","/Squiggly","/StrikeOut",
					"/Contents","/QuadPoints",

					/*Additional entries specific to a caret annotation*/
					/*Table 8.31*/
					"/Subtype","/RD","/Sy","/P","/None",
					
					/*Additional entries specific to a rubber stamp annotation*/
					/*Table 8.32*/
					"/Subtype","/Stamp","/Contents","/Name",
					"/Approced","/AsIs","/Confidential","/Departmental","/Draft",
					"/Experimental","/Expired","/Final","/ForComment",
					"/ForPublicRelease","/NotApproced","/NotForPublicRelease",
					"/Sold","/TopSecret",

					/*Additional entries specific to an ink annotation*/
					/*Table 8.33*/
					"/Subtype","/Ink","/Contents","/InkList","/BS",

					/*Additional entries specific to a pop-up annotation*/
					/*Table 8.34*/
					"/Subtype","/Popup","/Parent","/Open",
				
					
					/*Additional entries specific to a screen annotation*/
					/*Table 8.38*/
					"/Subtype","/Screen","/T","/MK","/BS",

					/*Entries in an appearance characteristics dictionary*/
					/*Table 8.40*/
					"/R","/BC","/BG","/CA","/RC","/AC","/I","/RI","/IX","/IF","/TP",

					/*Additional entries specific to a watermark annotation*/
					/*Table 8.41*/
					"/Subtype",	"/Watermark","/FixedPrint",

					/*Entries in a fixed print dictionary*/
					/*Table 8.42*/
					"/Type","/FixedPrint","/Matrix","/H","/V",


					/*Action types*/
					/*Table 8.48*/
					"/GoTo","/Thread","/Named","/SetOCGState","/Rendition","/Trans",
					

					/*Additional entries specific to a go-to action*/
					/*Table 8.49*/
					"/S","/GoTo","/D",
					
					/*Additional entries specific to a thread action*/
					/*Table 8.55*/
					"/S","/Thread","/F","/D","/B",

					/*Additional entries specific to a named actions*/
					/*Table 8.62*/
					"/S","/Named","/N","/NextPage","/PrevPage","/FirstPage","/LastPage",
					
					/*Additional entries specific to a set-OCG-state action*/
					/*Table 8.63*/
					"/S","/SetOCGState","/State","/ON","/OFF","/Toggle",
					"/PreserveRB",

					/*Additional entries specific to a rendition action*/
					/*Table 8.64*/
					"/S","/Redition","/R","/AN","/OP",

					/*Additional entries specific to a transition action*/
					/*Table 8.65*/
					"/S","/Trans","/Name",

					/*Additional entries specific to a signature field*/
					/*Table 8.81*/
					"/Lock","/SV",
					
					/*Entries in a signature field lock dictionary*/
					/*Table 8.82*/
					"/Type","/SigFieldLock","/Action","/All","/Include","/Exclude",
					"/Fields",
					
					/*Entries in a signature field seed value dictionary*/
					/*Table 8.83*/
					"/Type","/SV","/Filter","/SubFilter",
					"/DigestMethod","/SHA1","/SHA256","/SHA384","/SHA512",
					"/RIPEMD160",
					"/V","/Cert","/Reasons","/MDP","/TimeStamp","/Ff",
					"/LegalAttestation","/AddRevInfo","/Ff",

					/*Entries in a certificate seed value dictionary*/
					/*Table 8.84*/
					"/Type","/SVCert","/Subject","/SubjectDN","/KeyUsage","/Issuer",
					"/OID","/Ff",
					
					/*Entries in a signature dictionary*/
					/*Table 8.102*/
					"/Type","/Sig",
					"/Filter","/Adobe.PPKLite","/Entrust.PPKEF","/CICI.SignIt",
					"/VeriSign.PPKVS",
					"/SubFilter",
					"/adbe.x509.rsa_sha1","/adbe.pkcs7.detached","/adbe.pkcs7.sha1",
					"/ByteRange","/Contents","/Name","/M","/Location","/Reason",
					"/Reference","/Changes","/ContactInfo",
					"/R","/V","/Prop_Build","/Prop_AuthTime","/Prop_AuthType",

					/*Entries in a signature reference dictionary*/
					/*Table 8.103*/
					"/Type","/SigRef","/TransformMethod",
					"/DocMDP","/UR","/FieldMDP","/Identify",
					"/TransformParams","/Data","/DigestMethod",
					"/DigestValue","/DigestLocation",

					/*Entries in the DOcMDP transform parameters dictionary*/
					/*Table 8.104*/
					"/Type","/TransformParams",	"/P","/V","/1.2",

					/*Entries in the UR transform parameters dictionary*/
					/*Table 8.105*/
					"/Type","/TransformParams","/Document","/FullSave","/Msg",
					"/V","/2.2","/Annots","/Create","/Delete","/Modify","/Copy",
					"/Import","/Export","/Online","/SummaryView","/Form","/FillIn",
					"/SubmitStandalone","/SpawnTemplate","/BarcodePlaintext","/Online",
					"/FormEx","/BarcodePlaintext","/Signature","/EF","/P",

					/*Entries in a permissions dictionary*/
					/*Table 8.107*/
					"/DocMDP","/UR","/UR3",

					/*Entries in a legal attestation dictionary*/
					/*Table 8.108*/
					"/JavaScriptActions","/LaunchActions","/URIActions",
					"/MovieActions","/SoundActions","/HideAnnotationActions",
					"/GoToRemoveActions","/AlternateImages","/ExternalStreams",
					"/TrueTypeFonts","/ExternalRefXobjects","/ExternalOPIdicts",
					"/NonEmbeddedFonts","/DevDepGS_OP","/DevDepGS_HT","/DevDepGS_TR",
					"/DevDepGS_UCR","/DevDepGS_BG","/DevDepGS_FL","/Annotations",
					"/OptionalContent","/Attestation",

					/*Entries in a viewport dictionary*/
					/*Table 8.109*/
					"/Type","/Viewport","/BBox","/Name","/Measure",

					/*Entries in a measure dictionary*/
					/*Table 8.110*/
					"/Type","/Measure","/Subtype","/RL",
					
					/*Additional entries in a rectlinear measure dictionary*/
					/*Table 8.111*/
					"/R","/X","/Y","/D","/A","/T","/S","/O","/CYX",

					/*Entries in a number format dictionary*/
					/*Table 8.112*/
					"/Type","/NumberFormat","/U","/C","/F","/D","/R","/T",
					"/D","/FD","/RT","/RD","/PS","/SS","/O","/S","/P",
					
					
					/****
					 * 
					 * MultiMedia Interchange
					 * Not allowed					
					 */
					
					/***
					 * 
					 * 10. Document interchange
					 * 
					 */

					/*Predefined procedure sets*/
					/*Table 10.1*/
					"/PDF","/Text","/ImageB","/ImageC","/ImageI",

					/*Entries in the document information dictionary*/
					/*Table 10.2*/
					"/Title","/Author","/Subject","/Keywords","/Creator",
					"/Producer","/CreationDate","/ModDate",
					"/Trapped",	"/True","/False","/Unknown",

					/*Additional entries in a metadata stream dictionary*/
					/*Table 10.3*/
					"/Type","/Metadata","/Subtype","/XML",

					/*Additional entries components having metadata*/
					/*Table 10.4*/
					"/Metadata",

					/*Entries in a application data dictionary*/
					/*Table 10.6*/
					"/LastModified",
					"/Private",
					
					/*Entries in the mark information dictionary*/
					/*Table 10.8*/
					"/Marked","/UserProperties","/Suspects",
					
					/*Entries in the structure tree root*/
					/*Table 10.9*/
					"/Type","/StructTreeRoot","/K","/IDTree","/ParentTree",
					"/ParentTreeNextKey","/RoleMap","/ClassMap",

					/*Entries in a structure element dictionary*/
					/*Table 10.10*/
					"/Type","/StructElem","/S","/P","/ID","/Pg",
					"/K","/A","/C","/R","/T","/Lang","/Alt","/E","/ActualText",


					/*Entries in a marked-content reference dictionary*/
					/*Table 10.11*/
					"/Type","/MCR","/Pg","/Stm","/StmOwn","/MCID",

					/*Entries in an object reference dictionary*/
					/*Table 10.12*/
					"/Type","/OBJR","/Pg","/Obj",

					/*Additional dictionary entries for structure element access*/
					/*Table 10.13*/
					"/StructParent","/StructParents",

					/*Entry commin to all attributes objects*/
					/*Table 9.14*/
					"/O",

					/*Additional entries in an attribute object dictionary for user properties*/
					/*Table 10.15*/
					"/O","/UserProperties","/P",

					/*Entries in a user property dictionary*/
					/*Table 10.16*/
					"/N","/V","/F","/H",

					/*Property list entries for artifacts*/
					/*Table 10.17*/
					"/Type","/Pagination","/Layout","/Page","/Background",
					"/BBox","/Attached","/Header","/Bottom","/Footer","/Watermark","/Subtype",

					/*Standard structure tpes for grouping elements*/
					/*Table 10.20*/
					"/Document","/Part","/Art","/Sect","/Div","/BlockQuote","/Caption","/TOC","/TOCI","/Index","/NonStruct","/Private",

					/*Standard structure typesfor paragraphlike elements*/
					/*Table 10.22*/
					"/H","/H1","/H2","/H3","/H4","/H5","/H6","/P",

					/*Standard structure types for list elements*/
					/*Table 10.23*/
					"/L","/LI","/Lbl","/LBody",

					/*Standard structure types for table elements*/
					/*Table  10.24*/
					"/Table","/TR","/TH","/TD","/THead","/TBody","/TFoot",

					/*Standard structure types for inline-level structure elements*/
					/*Table 10.25*/
					"/Span","/Quote","/Note","/Reference","/BibEntry","/Code","/Annot","/Ruby","/Warichu",

                    /* Standard structure types for illustration elements*/
                    /*Table 10.27*/
                    "/Figure","/Formula",


    /*Standard layout attributes commin to all standard structure types*/
					/*Table 10.30*/
					"/Placement",
					"/Block","/Inline","/Before","/Start","/End",
					"/WritingMode",
					"/LrTb","/RlTb","/TbRl",
					"/BackgroundColor","/BorderColor","/BorderStyle","/None",
					"/Hidden","/Dotted","/Dashed","/Solid","/Double","/Groove","/Ridge","/Inset","/Outset",
					"/BorderThickness","/Padding","/Color",

					/*Additional standard layout attributes specific to block-level structure elements*/
					/*Table 10.31*/
					"/SpaceBefore","/SpaceAfter",
					"/StartIndent","/EndIndent",
					"/TextIndent","/TextAlign",
					"/Start","/Center","/End","/Justify",
					"/BBox","/Width","/Height",
					"/BlockAlign","/Before","/Middle","/After","/Justify",
					"/InlineAlign","/Start","/Center","/End",
					"/TBorderStyle","/TPadding",

					/*Standard layout attributes specific to inline-level structure elements*/
					/*Table 10.32*/
					"/LineHeight","/Normal","/Auto","/BaselineShift","/TextDecorationType",
					"/None","/Underline","/Overline","/LineThrough",
					"/TextDecorationColor","/TextDecorationThickness",
					"/RubyAlign","/RubyPosition","/GlyphOrientationVertical",
					"/Start","/Center","/End","/Justify","/Distribute",
					"/Before","/After","/Warichu","/Inline","/Auto",

					/*Standard column attributes*/
					/*Table 10.33*/
					"/ColumnCount","/ColumnGap","/ColumnWidth",
					
					/*Standard list attribute*/
					/*Table 10.34*/
					"/ListNumbering",
					"/None","/Disc","/Circle","/Square","/Decimal","/UpperRoman","/LowerRoman","/UpperAlpha","/LowerAlpha",
					
					/*PrintField attributes*/
					/*Table 10.35*/
					"/Role","/rb","/cb","/pb","/tv","/checked","/Desc","/on","/off","/neutral",
					
					/*Standard table attributes*/
					/*Table 10.36*/
					"/RowSpan","/ColSpan","/Headers","/Scope",
					"/Row","/Column","/Both","/Summary",


					/*Entries in a box color information*/
					/*Table 10.46*/
					"/CropBox","/BleedBox","/TrimBox","/ArtBox",

					/*Entries in a box style dictionary*/
					/*Table 10.47*/
					"/C","/W","/S","/D",

					/*Additional entries specific to a printers mark annotation*/
					/*Table 10.48*/
					"/Subtype","/PrinterMark","/MN",

					/*Additional entries specific t a prints mark form dictionary*/
					/*Table 10.49*/
					"/MarkStyle","/Colorants",

					/*Entries in a separation dictionary*/
					/*Table 10.50*/
					"/Pages","/DeviceColorant","/ColorSpace",

					/*Entries in a PDF/X output intent dictionary*/
					/*Table 10.51*/
					"/Type","/OutputIntent","/S",
					"/GTS_PDFX","/GTS_PDFA1","/OutputCondition","/OutputConditionIdentifier",
					"/RegistryName","/Info","/DestOutputProfile",

					/*Additional entries to a trap network annotation*/
					/*Table 10.52*/
					"/Subtype","/TrapNet","/Contents","/LastModified","/Version","/AnnotStates","/FontFauxing",

					/*Additional entries specific to a trap network appearance stream*/
					/*Table 10.53*/
					"/PCM","/DeviceGray","/DeviceRGB","/DeviceCMYK","/DeviceCMY","/DeviceRGBK","/DeviceN",
					"/SeparationColorNames","/TrapRegions","/TrapStyles",

					/***
					 * Appendix F
					 * 
					 * */
					
					/*Entries in the linearization parameter dictionary*/
					/*Table F.1*/
					"/Linearized","/L","/H","/O","/E","/N","/T","/P",
					
					/*
					The Digital Signature Build Properties Dictionary
					*/
					/*Entries in the build properties dictionary that are used by Adobe Acrobat*/
					/*Table 2.1*/
					"/Filter","/PubSec","/App","/SigQ",

					/*Common entries in build data dictionaries*/
					/*Table 2.2*/
					"/Name","/Date","/R","/PreRelease","/OS",
					"/NonEFontNoWarn","/TrustedMode","/V",

					/*Additional entries in the build data dictionary when used as the App dictionary in a build properties dictionary*/
					/*Table 2.3*/
					"/REx",

					/*Additional entries in the build data dictionary when used as the SigQ dictionary in a build properties dictionary
					Table 2.4*/
					"/Preview",

					/*Postscript
					 * 
					 * */
					
					"/CIDInit","/CMapType","/F9+0"

                    ,"/Endnote", "/Textbox", "/Workbook", "/Worksheet", "/Macrosheet", "/Annotation", "/Dialogsheet", "/Chartsheet", "/Diagram", "/Footnote", "/Chart", "/Slide", "/InlineShape", "/Artifact"
			}));	


	static HashSet<String> ALLOWEDTYPES = new HashSet<String>(Arrays.asList(
			new String[] {"/FontDescriptor","/Page","/Encoding","/ExtGState", "/ColorSpace","/Pattern","/Shading","/XObject","/Font","/ProcSet","/Properties","/BaseFont","/Name", "/Dests", "/Dest","/Info","/Metadata","/CMapName","/Image"}));

	static String [] blackList = {"/Action","/A","/FileSpec","/FunctionType", "/URLS","/EmbeddedFiles","/JavaScript","/F","/FFilter", "/FDecodeParms","/OpenAction","/AA","/URI","/AcroForm","/SpiderInfo","/Dur"}	;


    static String[] VALIDFONTPOSTFIX =
            new String[] {
                    "-BG-EUC-H","-BG-EUC-V","-GBpc-EUC-H","-GBpc-EUC-V",
                    "-GBK-EUC-H","-GBK-EUC-V","-GBKp-EUC-H","-GBKp-EUC-H",
                    "-GBK2K-H","-GBK2K-V","-UniGB-UCS2-H","-UniGB-UCS2-V",
                    "-UniGB-UTF16-H","-UniGB-UTF16-V","-B5pc-H","-B5pc-V",
                    "-HKscs-B5-H","-HKscs-B5-V","-ETen-B5-H","-ETen-B5-V",
                    "-ETenms-B5-H","-ETenms-B5-V","-CNS-EUC-H","-CNS-EUR-V",
                    "-UniCNS-UCS2-H","-UniCNS-UCS2-V","-UniCNS-UTF16-H",
                    "-UniCNS-UTF16-V","-83pv-RKSJ-H","-90ms-RKSJ-H","-90ms-RKSJ-V",
                    "-90msp-RKSJ-H","-90msp-RKSJ-V","-90pv-RKSJ-H","-Add-RKSJ-H",
                    "-Add-RKSJ-V","-EUC-H","-EUC-V","-Ext-RKSJ-H","-Ext-RKSJ-V",
                    "-UniJIS-UCS2-H","-UniJIS-UCS2-V","-UniJIS-UCS2-HW-H",
                    "-UniJIS-UCS2-HW-V","-UniJIS-UTF16-H","-UniJIS-UTF16-V",
                    "-KSC-EUC-H","-KSC-EUC-V","-KSCms-UHC-H","-KSCms-UHC-V",
                    "-KSCms-UHC-HW-H","-KSCms-UHC-HW-V","-KSCpc-EUC-H","-UniKS-UCS2-H",
                    "-UniKS-UCS2-V","-UniKS-UTF16-H","-UniKS-UTF16-V","-Identity-H",
                    "-Identity-V"

            };

}


