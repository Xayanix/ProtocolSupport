package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleInventoryOpen;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class InventoryOpen extends MiddleInventoryOpen {

	@Override
	public RecyclableCollection<ClientBoundPacketData> toData() {
		ProtocolVersion version = connection.getVersion();
		ClientBoundPacketData serializer = ClientBoundPacketData.create(ClientBoundPacket.PLAY_WINDOW_OPEN_ID);
		serializer.writeByte(windowId);
		serializer.writeByte(IdRemapper.INVENTORY.getTable(version).getRemap(type).toLegacyId());
		StringSerializer.writeString(serializer, version, title.toLegacyText(cache.getAttributesCache().getLocale()));
		serializer.writeByte(slots);
		return RecyclableSingletonList.create(serializer);
	}

}
